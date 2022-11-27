// #full-example
package com.example.helloworld;

import akka.NotUsed;
import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import akka.actor.typed.ActorSystem;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static akka.NotUsed.notUsed;
import static org.junit.Assert.assertEquals;

public class AkkaGreeterServiceTest {

    @ClassRule
    public static final TestKitJunitResource TEST_KIT = new TestKitJunitResource();

    private static final ActorSystem<?> ACTOR_SYSTEM = TEST_KIT.system();
    private static GreeterService service;

    @BeforeClass
    public static void setup() {
        service = new AkkaGreeterService(ACTOR_SYSTEM);
    }

    @Test
    public void greeterServiceRepliesToSingleRequest() throws Exception {
        HelloReply reply = service.sayHello(buildHelloRequest("Bob"))
                .toCompletableFuture()
                .get(5, TimeUnit.SECONDS);
        HelloReply expected = HelloReply.newBuilder().setMessage("Hello, Bob").build();
        assertEquals(expected, reply);
    }

    @Test
    public void keepSayingHelloTest() throws ExecutionException, InterruptedException, TimeoutException {
        Source<HelloRequest, NotUsed> source = Source.from(Arrays.asList("Alice", "Bob")).map(this::buildHelloRequest);

        final Source<HelloReply, NotUsed> sourceUnderTest = service.keepSayingHello(source);
        final Sink<HelloReply, CompletionStage<List<HelloReply>>> collectorSink = Sink.seq();

        final CompletionStage<List<HelloReply>> sourceUnderTestAttachedToSink = sourceUnderTest.runWith(collectorSink, ACTOR_SYSTEM);


        final List<HelloReply> helloReplies = sourceUnderTestAttachedToSink.toCompletableFuture().get(3, TimeUnit.SECONDS);

        final List<HelloReply> expectedReplies = Stream.of("Hello Alice!", "Hello Bob!").map(message -> HelloReply.newBuilder().setMessage(message).build()).collect(Collectors.toList());

        assertEquals(expectedReplies, helloReplies);


    }


    @Test
    public void keepSayingHelloRangeTest() throws ExecutionException, InterruptedException, TimeoutException {

        Source<HelloRequest, NotUsed> unboundedSource = Source.range(1, 10)
                .map(index -> "Alice-" + index)
                .map(this::buildHelloRequest)
                .mapMaterializedValue(ignored->notUsed());

        final Source<HelloReply, NotUsed> sourceUnderTest = service.keepSayingHello(unboundedSource);
        final Sink<HelloReply, CompletionStage<List<HelloReply>>> collectorSink = Sink.seq();

        final CompletionStage<List<HelloReply>> sourceUnderTestAttachedToSink = sourceUnderTest.runWith(collectorSink, ACTOR_SYSTEM);


        final List<HelloReply> helloReplies = sourceUnderTestAttachedToSink.toCompletableFuture().get(3, TimeUnit.SECONDS);

        final List<HelloReply> expectedReplies = IntStream.range(1, 11).mapToObj(index-> "Hello Alice-" + index + "!").map(this::buildHelloReply).collect(Collectors.toList());

        assertEquals(expectedReplies, helloReplies);


    }

    private <T> HelloRequest buildHelloRequest(T name) {
        return HelloRequest.newBuilder().setName(name.toString()).build();
    }


    private <T> HelloReply buildHelloReply(T message) {
        return HelloReply.newBuilder().setMessage(message.toString()).build();
    }

}
// #full-example
