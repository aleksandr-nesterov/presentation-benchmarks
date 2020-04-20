package org.ing.benchmarks;

import com.sun.corba.se.impl.oa.poa.AOMEntry;
import org.ing.support.ContendedAtomicLong;
import org.ing.support.PaddedAtomicLong;

import java.util.concurrent.atomic.AtomicLong;

public class FalseSharingRunner {

    private static final int THREADS_NUMBER = 8;
    private static final int ITERATIONS = 20_000_000;

    private static final AtomicLong[] longs = new AtomicLong[THREADS_NUMBER];

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < THREADS_NUMBER; i++) {
            longs[i] = new PaddedAtomicLong();
        }
        long nanos = System.nanoTime();
        Thread[] thread = new Thread[THREADS_NUMBER];
        for (int i = 0; i < THREADS_NUMBER; i++) {
            thread[i] = new Thread(new MyRunnable(i));
        }
        for (int i = 0; i < THREADS_NUMBER; i++) {
            thread[i].start();
        }
        for (int i = 0; i < THREADS_NUMBER; i++) {
            thread[i].join();
        }
        System.out.println("duration = " + (System.nanoTime() - nanos));
    }

    private static class MyRunnable implements Runnable {

        private final int index;

        private MyRunnable(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < ITERATIONS) {
                longs[index].set(i);
            }
        }
    }

}
