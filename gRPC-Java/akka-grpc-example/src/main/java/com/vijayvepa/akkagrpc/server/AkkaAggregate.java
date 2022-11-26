package com.vijayvepa.akkagrpc.server;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.SupervisorStrategy;
import akka.actor.typed.javadsl.Behaviors;
import akka.cluster.sharding.typed.javadsl.ClusterSharding;
import akka.cluster.sharding.typed.javadsl.Entity;
import akka.cluster.sharding.typed.javadsl.EntityTypeKey;
import akka.persistence.typed.PersistenceId;
import akka.persistence.typed.javadsl.*;
import com.vijayvepa.akkagrpc.server.command.Command;
import com.vijayvepa.akkagrpc.server.command.DisableAkka;
import com.vijayvepa.akkagrpc.server.command.GetAkka;
import com.vijayvepa.akkagrpc.server.command.RegisterAkka;
import com.vijayvepa.akkagrpc.server.event.Event;
import com.vijayvepa.akkagrpc.server.event.AkkaDisabled;
import com.vijayvepa.akkagrpc.server.event.AkkaRegistered;
import com.vijayvepa.akkagrpc.server.reply.Accepted;
import com.vijayvepa.akkagrpc.server.reply.Rejected;
import com.vijayvepa.akkagrpc.server.reply.Summary;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AkkaAggregate extends EventSourcedBehaviorWithEnforcedReplies<Command, Event, Akka> {

    public final String akkaId;
    public final String projectionTag;
    public static EntityTypeKey<Command> ENTITY_KEY = EntityTypeKey.create(Command.class, "AkkaAggregate");

    private AkkaAggregate(String akkaId, String projectionTag) {
        super(PersistenceId.of(ENTITY_KEY.name(), akkaId),
                SupervisorStrategy.restartWithBackoff(Duration.ofMillis(200), Duration.ofSeconds(5), 0.1));
        this.akkaId = akkaId;
        this.projectionTag = projectionTag;
    }

    public static Behavior<Command> create(String akkaId, String projectionTag) {
        return Behaviors.setup(
                ctx -> EventSourcedBehavior.start(new AkkaAggregate(akkaId, projectionTag), ctx));
    }

    public static final List<String> TAGS =
            Collections.unmodifiableList(
                    Arrays.asList("akka-grpc-example-0", "akka-grpc-example-1", "akka-grpc-example-2", "akka-grpc-example-3", "akka-grpc-example-4"));

    public static void init(ActorSystem<?> system) {
        ClusterSharding.get(system)
                .init(
                        Entity.of(
                                ENTITY_KEY,
                                entityContext -> {
                                    int i = Math.abs(entityContext.getEntityId().hashCode() % TAGS.size());
                                    String selectedTag = TAGS.get(i);
                                    return AkkaAggregate.create(entityContext.getEntityId(), selectedTag);
                                }));
    }

    @Override
    public Akka emptyState() {
        return Akka.EMPTY;
    }

    @Override
    public RetentionCriteria retentionCriteria() {
        return RetentionCriteria.snapshotEvery(100, 2);
    }

    @Override
    public Set<String> tagsFor(Event event) {
        return Collections.singleton(projectionTag);
    }

    @Override
    public EventHandler<Akka, Event> eventHandler() {
        return newEventHandlerBuilder()
                .forAnyState()
                .onEvent(AkkaRegistered.class, (state, evt) -> new Akka(evt.getAkkaId(),
                        evt.getTitle(), evt.getReleaseYear(), evt.getRating(), evt.getGenre(),
                        evt.getCreatedBy(), null, evt.getCreatedDateTime(), null, "NEW"))
                .onEvent(AkkaDisabled.class, (state, evt) -> new Akka(evt.getAkkaId(),
                        state.getTitle(), state.getReleaseYear(), state.getRating(),
                        state.getGenre(), state.getCreatedBy(), state.getLastModifiedBy(),
                        state.getCreationDateTime(), state.getLastModifiedDateTime(), "DISABLED"))
                .build();
    }

    @Override
    public CommandHandlerWithReply<Command, Event, Akka> commandHandler() {
        CommandHandlerWithReplyBuilder<Command, Event, Akka> builder = newCommandHandlerWithReplyBuilder();

        builder.forState(Akka::isEmpty)
                .onCommand(RegisterAkka.class, this::onRegisterAkka);

        builder.forState(Akka::isNew)
                .onCommand(DisableAkka.class, this::onDisableAkka);

        builder.forState(Akka::isDisabled)
                .onCommand(DisableAkka.class, cmd -> Effect().reply(cmd.replyTo, new Rejected("Cannot disable already disabled")));

        builder.forAnyState().onCommand(GetAkka.class, this::onGet);

        return builder.build();
    }

    private ReplyEffect<Event, Akka> onRegisterAkka(Akka akka, RegisterAkka cmd) {
        return Effect()
                .persist(new AkkaRegistered(akkaId, cmd.getAkkaDetails().getTitle(),
                        cmd.getAkkaDetails().getReleaseYear(),
                        cmd.getAkkaDetails().getRating(),
                        cmd.getAkkaDetails().getGenre(),
                        cmd.getCreatedBy(), Instant.now().toString()))
                .thenReply(cmd.replyTo, s -> new Accepted(toSummary(s)));
    }

    private ReplyEffect<Event, Akka> onDisableAkka(Akka akka, DisableAkka cmd) {
        return Effect()
                .persist(new AkkaDisabled(akkaId,
                        cmd.getDisabledBy(), Instant.now().toString()))
                .thenReply(cmd.replyTo, s -> new Accepted(toSummary(s)));

    }

    private ReplyEffect<Event, Akka> onGet(Akka akka, GetAkka cmd) {
        return Effect().reply(cmd.replyTo, toSummary(akka));
    }

    private Summary toSummary(Akka akka) {
        return new Summary(akka.getAkkaId(), akka.getTitle(), akka.getReleaseYear(), akka.getRating(), akka.getGenre(), akka.getCreatedBy(), akka.getLastModifiedBy(), akka.getCreationDateTime(), akka.getLastModifiedDateTime(), akka.getSmStatus());
    }
}
