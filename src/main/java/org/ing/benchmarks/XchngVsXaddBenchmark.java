package org.ing.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Measurement(iterations = 10)
@Warmup(iterations = 3)
public class XchngVsXaddBenchmark {

    /*
     *  We want to measure getAndIncrement of AtomicLong depending on the JDK version.
     */
    @org.openjdk.jmh.annotations.State(Scope.Benchmark)
    public static class State {
        AtomicLong counter;

        @Setup
        public void setup() {
            counter = new AtomicLong();
        }
    }

    @Benchmark
    public long getAndIncrement(State state) {
        return state.counter.getAndIncrement();
    }

}
