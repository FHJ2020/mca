package thread.three;


import java.util.concurrent.CountDownLatch;

public class CacheLinePaddingMain {

    static T2[] array = new T2[2];
    static {
        array[0] = new T2();
        array[1] = new T2();
    }


    static class T2 {
        long p1,p2,p3,p4,p5,p6,p7;
        volatile  long p=0l;
        long a1,a2,a3,a4,a5,a6,a7;
    }


    public static void main(String[] args) throws InterruptedException {

        notInCacheLine(1_0000_0000l);
    }



    private static void notInCacheLine(long count) throws InterruptedException {


        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < count; i++) {
                    array[0].p = i;
                }
                latch.countDown();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < count; i++) {
                    array[1].p = i;
                }
                latch.countDown();
            }
        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        latch.await();
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
