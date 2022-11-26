package com.vijayvepa.akkagrpc.query;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.vijayvepa.akkagrpc.server.Akka;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AkkaDAO {
    Mono<Akka> getAkkaById(String akkaId);

    Source<Akka, NotUsed> getAkkaByAkkaId(String akkaId);

    Flux<Akka> getAkkas();
}
