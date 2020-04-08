package org.ing.benchmarks.other;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class FalseSharingBenchmark {

    public static final int ITERATIONS = 10_000_000;
    public static final int THREADS_NUMBER = 4;

    @State(Scope.Benchmark)
    public static class DefaultState {

        private final AtomicLong[] longs = new AtomicLong[THREADS_NUMBER];

        @Setup
        public void setup() {
            for (int i = 0; i < THREADS_NUMBER; i++) {
                longs[i] = new AtomicLong();
            }
        }
    }

    @Benchmark
    public void atomicLongSet(DefaultState state) {
        int i = 0;
        int index = 0;
        while (i++ < ITERATIONS) {
            state.longs[index++].set(i);
            if (index == THREADS_NUMBER)
                index = 0;
        }
    }


}
