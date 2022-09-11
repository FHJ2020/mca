package juc;

import thread.SleepHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchMain {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);


        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    SleepHelper.sleepMillsSecond(1);
                    latch.countDown();

                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        latch.await();
        System.out.println("go");


    }
}
