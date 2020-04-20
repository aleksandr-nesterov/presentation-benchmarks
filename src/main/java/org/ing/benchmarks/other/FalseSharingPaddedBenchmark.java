package org.ing.benchmarks.other;

import org.ing.support.PaddedAtomicLong;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.ing.benchmarks.other.FalseSharingBenchmark.ITERATIONS;
import static org.ing.benchmarks.other.FalseSharingBenchmark.THREADS_NUMBER;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class FalseSharingPaddedBenchmark {


    /*
     *  We want to measure incrementAndGet of AtomicLong depending on the JDK version.
     */
    @State(Scope.Benchmark)
    public static class PaddedState {

        private final PaddedAtomicLong[] longs = new PaddedAtomicLong[THREADS_NUMBER];

        @Setup
        public void setup() {
            for (int i = 0; i < THREADS_NUMBER; i++) {
                longs[i] = new PaddedAtomicLong();
            }
        }
    }

    @Benchmark
    public void paddedAtomicLongSet(PaddedState state) {
        int i = 0;
        int index = 0;
        while (i++ < ITERATIONS) {
            state.longs[index++].set(i);
            if (index == THREADS_NUMBER)
                index = 0;
        }
    }

}
