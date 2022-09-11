package juc;

import thread.SleepHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierMain {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("go");
            }
        });

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SleepHelper.sleepMillsSecond(1);
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

    }
}
