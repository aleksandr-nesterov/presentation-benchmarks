package org.ing.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class AtomicLongBenchmark {

    /*
     *  We want to measure incrementAndGet of AtomicLong depending on the JDK version.
     */
    @State(Scope.Benchmark)
    public static class CounterState {
        AtomicLong counter;

        @Setup
        public void setup() {
            counter = new AtomicLong();
        }
    }

    @Benchmark
    public long incrementAndGet(CounterState counterState) {
        return counterState.counter.incrementAndGet();
    }

}
