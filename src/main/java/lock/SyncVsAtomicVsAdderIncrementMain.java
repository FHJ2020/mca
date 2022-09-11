package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class SyncVsAtomicVsAdderIncrementMain {

    static long syncLong = 0L;

    public static void main(String[] args) throws InterruptedException {

        longAdder(1000);

        atomicLong(1000);
        syncLong(1000);
    }

    private static void syncLong(int threadNum) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        Object lock = new Object();

        long start = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 100000; i1++) {
                        synchronized (lock) {
                            syncLong++;
                        }
                    }
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("syncLong=============");
        System.out.println(syncLong);
        System.out.println("syncLong: " + (System.currentTimeMillis() - start));
    }

    private static void atomicLong(int threadNum) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        long start = System.currentTimeMillis();
        AtomicLong atomicLong = new AtomicLong();
        for (int i = 0; i < threadNum; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 100000; i1++) {
                        atomicLong.incrementAndGet();
                    }
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("AtomicLong=============");
        System.out.println(atomicLong.get());
        System.out.println("AtomicLong: " + (System.currentTimeMillis() - start));
    }

    private static void longAdder(int threadNum) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        long start = System.currentTimeMillis();
        LongAdder longAdder = new LongAdder();
        for (int i = 0; i < threadNum; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 100000; i1++) {
                        longAdder.increment();
                    }
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("AtomicLong=============");
        System.out.println(longAdder);
        System.out.println("longAdder: " + (System.currentTimeMillis() - start));
    }
}
