package juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchOneMain {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(20);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (latch.getCount() > 0) {
                    System.out.println(latch.getCount());
                    latch.countDown();
                }
            }
        });
        thread.start();

        latch.await();
        System.out.println("go");


    }
}
