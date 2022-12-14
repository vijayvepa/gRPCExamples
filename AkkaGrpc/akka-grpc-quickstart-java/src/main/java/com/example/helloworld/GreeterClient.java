package com.example.helloworld;

//region import

import akka.Done;
import akka.NotUsed;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.Behaviors;
import akka.grpc.GrpcClientSettings;
import akka.japi.Pair;
import akka.stream.javadsl.Source;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static akka.NotUsed.notUsed;
//endregion import

//region client-request-reply
class GreeterClient {

    public static void main(String[] args) {
        final ActorSystem<Void> actorSystem = ActorSystem.create(Behaviors.empty(), "GreeterClient");

        GreeterServiceClient greeterServiceClient = GreeterServiceClient.create(
                GrpcClientSettings.fromConfig("helloworld.GreeterService", actorSystem),
                actorSystem
        );

        final List<String> names;
        if (args.length == 0) {
            names = Arrays.asList("Alice", "Bob");
        } else {
            names = Arrays.asList(args);
        }

        //region client-request-reply
        names.forEach(name -> {
            System.out.println("Performing request: " + name);
            HelloRequest request = HelloRequest.newBuilder()
                    .setName(name)
                    .build();
            CompletionStage<HelloReply> helloReplyCompletionStage = greeterServiceClient.sayHello(request);
            helloReplyCompletionStage.whenComplete((reply, error) -> {
                if (error == null) {
                    System.out.println(reply.getMessage());
                } else {
                    System.out.println(error.getMessage());
                }
            });
        });

        //endregion client-request-reply

        //region client-stream
        names.forEach(name -> {
            System.out.println("Performing streaming requests: " + name);

            Source<HelloRequest, NotUsed> requestStream =
                    Source
                            .tick(Duration.ofSeconds(1), Duration.ofSeconds(1), "tick")
                            .zipWithIndex()
                            .map(Pair::second)
                            .map(i ->
                                    HelloRequest.newBuilder()
                                            .setName(name + "-" + i)
                                            .build())
                            .mapMaterializedValue(ignored -> notUsed());

            Source<HelloReply, NotUsed> responseStream = greeterServiceClient.sayHelloToAll(requestStream);

            CompletionStage<Done> done =
                    responseStream.runForeach(
                            reply ->
                                    System.out.println(name + " got streaming reply: " + reply.getMessage()),
                            actorSystem);

            done.whenComplete((reply, error) -> {
                if (error == null) {
                    System.out.println("streamingBroadcast done");
                } else {
                    System.out.println("Error streamingBroadcast: " + error);
                }
            });

        });
        //endregion client-stream

    }
}
//endregion client-request-reply
