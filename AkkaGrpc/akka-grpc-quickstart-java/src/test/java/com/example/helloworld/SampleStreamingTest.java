package com.example.helloworld;

import akka.NotUsed;
import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import akka.actor.typed.ActorSystem;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;

public class SampleStreamingTest {


    @ClassRule
    public static final TestKitJunitResource TEST_KIT = new TestKitJunitResource();
    private static final ActorSystem<?> ACTOR_SYSTEM = TEST_KIT.system();

    /**
     * Testing a custom sink can be as simple as
     * <li> attaching a source that emits elements from a predefined collection </li>
     * <li> running a constructed test flow and</li>
     * <li>asserting on the results that sink produced.</li>
     *
     * @throws ExecutionException   execution failed
     * @throws InterruptedException interrupted
     * @throws TimeoutException     timed out
     */
    @Test
    public void testCustomSink() throws ExecutionException, InterruptedException, TimeoutException {
        final Sink<Integer, CompletionStage<Integer>> sinkUnderTest =
                Flow.of(Integer.class)
                        .map(i -> i * 2)
                        .toMat(Sink.fold(0, Integer::sum), Keep.right());


        final Source<Integer, NotUsed> source = Source.from(Arrays.asList(1, 2, 3, 4));

        final CompletionStage<Integer> sourceAttachedToSink =
                source.runWith(sinkUnderTest, ACTOR_SYSTEM);

        final Integer result = sourceAttachedToSink.toCompletableFuture().get(3, TimeUnit.SECONDS);

        assertEquals(20, result.intValue());
    }
}
