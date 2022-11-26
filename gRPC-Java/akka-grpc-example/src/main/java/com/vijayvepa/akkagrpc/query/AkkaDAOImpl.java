package com.vijayvepa.akkagrpc.query;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.typesafe.config.Config;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import com.vijayvepa.akkagrpc.server.Akka;
import com.vijayvepa.akkagrpc.util.AkkaORM;
import com.vijayvepa.akkagrpc.util.R2dbc;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AkkaDAOImpl implements AkkaDAO {

    private final ConnectionFactory connectionFactory;


    @Inject
    public AkkaDAOImpl(Config config) {

        String driver = config.getString("akka-grpc-example.dao.driver");
        String host = config.getString("akka-grpc-example.dao.host");
        int port = config.getInt("akka-grpc-example.dao.port");
        String database = config.getString("akka-grpc-example.dao.database");
        String user = config.getString("akka-grpc-example.dao.user");
        String password = config.getString("akka-grpc-example.dao.password");

        final ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, driver)
                .option(ConnectionFactoryOptions.HOST, host)
                .option(ConnectionFactoryOptions.PORT, port)
                .option(ConnectionFactoryOptions.DATABASE, database)
                .option(ConnectionFactoryOptions.USER, user)
                .option(ConnectionFactoryOptions.PASSWORD, password)
                .build();

        connectionFactory = ConnectionFactories.get(options);
    }

    @Override
    public final Mono<Akka> getAkkaById(String akkaId) {
        return Mono.usingWhen(connectionFactory.create(),
                connection ->
                        Mono.from(connection.createStatement("SELECT * FROM public.akka WHERE akkaid = $1")
                                        .bind("$1", akkaId)
                                        .execute())
                                .map(result -> result.map((row, rowMetadata) -> AkkaORM.mapRowToAkka(row)))
                                .flatMap(pub -> Mono.from(pub)),
                Connection::close);
    }

    @Override
    public final Source<Akka, NotUsed> getAkkaByAkkaId(String akkaId) {
        R2dbc r2dbc = new R2dbc(connectionFactory);
        return Source.fromPublisher(r2dbc.withHandle(
                handle -> {
                    return handle.select("SELECT * FROM public.akka WHERE akkaid = $1")
                            .bind("$1", akkaId)
                            .mapResult(result -> result.map((row, rowMetadata) -> AkkaORM.mapRowToAkka(row)));
                }
        ));
    }

    @Override
    public final Flux<Akka> getAkkas() {
        R2dbc r2dbc = new R2dbc(connectionFactory);
        return r2dbc.withHandle(
                handle -> {
                    return handle.select("SELECT * FROM public.akka")
                            .mapResult(result -> result.map((row, rowMetadata) -> AkkaORM.mapRowToAkka(row)));
                }
        );
    }
}
