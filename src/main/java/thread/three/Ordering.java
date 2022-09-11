package thread.three;

import thread.SleepHelper;

import java.util.concurrent.CountDownLatch;

public class Ordering {

        static volatile int a, b, x, y;
//        static  int a, b, x, y;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            CountDownLatch latch = new CountDownLatch(2);

            a = b = x = y = 0;

            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
                latch.countDown();
            });
            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
                latch.countDown();
            });
            t1.start();
            t2.start();
            latch.await();
            System.out.println(1);
            if (x == 0 && y == 0) {
                System.err.println(i + " : " + x + " , " + y);
                break;
            }
        }

    }
}
