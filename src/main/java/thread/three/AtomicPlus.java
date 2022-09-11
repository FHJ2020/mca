package thread.three;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPlus {

    static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        int count = 1_000_0000;
        int threadNum=32;
        CountDownLatch latch = new CountDownLatch(threadNum);

        long start=System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                for (int j = 0; j < count; j++) {
                    num.addAndGet(1);
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(num);

    }
}
