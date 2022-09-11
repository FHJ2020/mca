package thread.three;


import java.util.concurrent.CountDownLatch;

public class CacheLineMain {

    static T1[] array = new T1[2];
    static {
        array[0] = new T1();
        array[1] = new T1();
    }

    static class T1 {

        volatile long p=0;
    }



    public static void main(String[] args) throws InterruptedException {

        inCacheLine(1_0000_0000l);
    }

    private static void inCacheLine(long count) throws InterruptedException {


        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            for (long i = 0; i < count; i++) {
                array[0].p = i;
            }
            latch.countDown();
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < count; i++) {
                array[1].p = i;
            }
            latch.countDown();
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        latch.await();
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

}
