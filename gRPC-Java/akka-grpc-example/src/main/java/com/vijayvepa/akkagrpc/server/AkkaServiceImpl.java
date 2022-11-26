package com.vijayvepa.akkagrpc.server;

import akka.NotUsed;
import akka.Done;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.DispatcherSelector;
import akka.cluster.sharding.typed.javadsl.ClusterSharding;
import akka.cluster.sharding.typed.javadsl.EntityRef;
import akka.grpc.javadsl.Metadata;
import akka.stream.javadsl.Source;
import com.akkagrpc.akka.*;
import com.google.common.base.Strings;
import com.google.protobuf.Empty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import com.vijayvepa.akkagrpc.query.AkkaDAO;
import com.vijayvepa.akkagrpc.server.command.DisableAkka;
import com.vijayvepa.akkagrpc.server.command.GetAkka;
import com.vijayvepa.akkagrpc.server.command.RegisterAkka;
import com.vijayvepa.akkagrpc.server.reply.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vijayvepa.akkagrpc.server.command.Command;
import com.vijayvepa.akkagrpc.server.reply.Accepted;
import com.vijayvepa.akkagrpc.server.reply.Confirmation;
import com.vijayvepa.akkagrpc.server.reply.Rejected;
import com.vijayvepa.akkagrpc.util.ActiveDirectoryClient;
import com.vijayvepa.akkagrpc.util.MicrometerClient;
import com.vijayvepa.akkagrpc.util.TokenVerifier;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;

@Singleton
public final class AkkaServiceImpl implements AkkaServicePowerApi {
    private final Logger logger = LoggerFactory.getLogger(AkkaServiceImpl.class);
    private final Duration askTimeout;
    private final ClusterSharding clusterSharding;
    private final Executor blockingJdbcExecutor;
    private final MeterRegistry meterRegistry;
    private final Counter akkaCounter;
    private final AkkaDAO dao;
    private final ActiveDirectoryClient activeDirectoryClient;

    @Inject
    public AkkaServiceImpl(ActorSystem<?> system, AkkaDAO dao,
                                     MicrometerClient micrometerClient, ActiveDirectoryClient activeDirectoryClient) {
        this.dao = dao;
        this.activeDirectoryClient = activeDirectoryClient;
        DispatcherSelector dispatcherSelector = DispatcherSelector.fromConfig("akka.persistence.r2dbc.journal.plugin-dispatcher");
        this.askTimeout = system.settings().config().getDuration("akka-grpc-example.ask-timeout");
        this.clusterSharding = ClusterSharding.get(system);
        this.blockingJdbcExecutor = system.dispatchers().lookup(dispatcherSelector);
        this.meterRegistry = micrometerClient.monitoringSystem();
        this.akkaCounter = this.meterRegistry.counter("akka", "count", "type");

    }

    private EntityRef<Command> entityRef(String akkaId) {
        return clusterSharding.entityRefFor(AkkaAggregate.ENTITY_KEY, akkaId);
    }

    @Override
    public CompletionStage<GetAkkaResponse> getAkka(GetAkkaRequest in, Metadata metadata) {
        return entityRef(in.getAkkaId())
        .<Summary>ask(replyTo -> new GetAkka(in.getAkkaId(), replyTo), askTimeout)
        .thenApply(summary -> GetAkkaResponse.newBuilder()
            .setAkka(com.akkagrpc.akka.Akka.newBuilder()
            .setAkkaId(summary.getAkkaId())
            .setTitle(summary.getTitle())
            .setRating(summary.getRating())
            .setReleaseYear(summary.getReleaseYear())
            .setGenre(summary.getGenre())
            .build())
        .build());
    }

    @Override
    public Source<GetAkkasResponse, NotUsed> getAkkas(Empty in, Metadata metadata) {
        Flux<com.vijayvepa.akkagrpc.server.Akka> akkas = dao.getAkkas();
        return Source.from(akkas.toIterable())
        .map(akka -> {
        return GetAkkasResponse.newBuilder()
            .setAkka(com.akkagrpc.akka.Akka.newBuilder()
            .setAkkaId(akka.getAkkaId())
            .setTitle(akka.getTitle())
            .setRating(akka.getRating())
            .setReleaseYear(akka.getReleaseYear())
            .setGenre(akka.getGenre())
            .build())
        .build();
        });
    }

    @Override
    public CompletionStage<RegisterAkkaResponse> registerAkka(RegisterAkkaRequest in, Metadata metadata) {
        akkaCounter.increment(1.0);
        String akkaId = UUID.randomUUID().toString();
        return entityRef(akkaId)
        .<Confirmation>ask(replyTo ->
        new RegisterAkka(in, "Anonymous", replyTo), askTimeout)
        .thenApply(this::handleConfirmation)
        .thenApply(summary -> RegisterAkkaResponse.newBuilder()
            .setAkkaId(summary.getSummary().getAkkaId())
        .build());
    }

    @Override
    public CompletionStage<DisableAkkaResponse> disableAkka(DisableAkkaRequest in,Metadata metadata){
        return entityRef(in.getAkkaId())
        .<Confirmation>ask(replyTo ->
        new DisableAkka(in.getAkkaId(), "Anonymous", replyTo), askTimeout)
        .thenApply(this::handleConfirmation)
        .thenApply(accepted -> DisableAkkaResponse.newBuilder()
            .setResponse(Done.getInstance().toString())
        .build());
    }

    private Accepted handleConfirmation(Confirmation confirmation) {
        if (confirmation instanceof Accepted) {
            return (Accepted) confirmation;
        }
        Rejected rejected = (Rejected) confirmation;
        throw new RuntimeException(rejected.getReason());
    }

    private Set<String> verifyTokenAndGetUserRoles(String token) {
        Jws<Claims> jwt = TokenVerifier.parseJwt(token);
        if (!Strings.isNullOrEmpty(jwt.getBody().toString())) {
            final Map<String, Object> realmAccess = (Map<String, Object>) jwt.getBody().get("realm_access");
            final String username = jwt.getBody().get("preferred_username").toString();
            Set<String> roles = ((List<String>) realmAccess.get("roles")).stream().collect(Collectors.toSet());
            return roles;
        }
        return Collections.<String>emptySet();
    }

    private String getUserNameFromToken(String token) {
        Jws<Claims> jwt = TokenVerifier.parseJwt(token);
        if (!Strings.isNullOrEmpty(jwt.getBody().toString())) {
            return jwt.getBody().get("preferred_username").toString();
        }
        return "";
    }
}
