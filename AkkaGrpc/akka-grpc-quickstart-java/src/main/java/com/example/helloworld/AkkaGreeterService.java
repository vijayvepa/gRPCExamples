package com.example.helloworld;

//#import

import akka.NotUsed;
import akka.actor.typed.ActorSystem;
import akka.japi.Pair;
import akka.stream.javadsl.BroadcastHub;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.MergeHub;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

//#import

//#service-request-reply
//#service-stream
class AkkaGreeterService implements GreeterService {

    final ActorSystem<?> actorSystem;
    //#service-request-reply
    final Sink<HelloRequest, NotUsed> inboundHub;
    final Source<HelloReply, NotUsed> outboundHub;
    //#service-request-reply

    public AkkaGreeterService(ActorSystem<?> actorSystem) {
        this.actorSystem = actorSystem;
        //#service-request-reply
        Pair<Sink<HelloRequest, NotUsed>, Source<HelloReply, NotUsed>> helloRequestReply =
                MergeHub.of(HelloRequest.class)
                        .map(request ->
                                HelloReply.newBuilder()
                                        .setMessage("Hello, " + request.getName())
                                        .build())
                        .toMat(BroadcastHub.of(HelloReply.class), Keep.both())
                        .run(actorSystem);

        inboundHub = helloRequestReply.first();
        outboundHub = helloRequestReply.second();
        //#service-request-reply
    }

    @Override
    public CompletionStage<HelloReply> sayHello(HelloRequest request) {
        return CompletableFuture.completedFuture(
                HelloReply.newBuilder()
                        .setMessage("Hello, " + request.getName())
                        .build()
        );
    }

    //#service-request-reply
    @Override
    public Source<HelloReply, NotUsed> sayHelloToAll(Source<HelloRequest, NotUsed> in) {
        in.runWith(inboundHub, actorSystem);
        return outboundHub;
    }

    @Override
    public Source<HelloReply, NotUsed> keepSayingHello(Source<HelloRequest, NotUsed> in) {
        return in.map(request->
            HelloReply.newBuilder()
                    .setMessage("Hello " + request.getName() + "!")
                    .build()
        );
    }
    //#service-request-reply
}
//#service-stream
//#service-request-reply
