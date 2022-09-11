package thread.three;

import java.util.concurrent.CountDownLatch;

public class NumPlus {

    private static int num = 0;

    public static void main(String[] args) {
        int count = 1_0000;
        int threadNum = 100;
        CountDownLatch latch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < count; j++) {
                    synchronized (NumPlus.class) {
                        num = num + 1;
                    }
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
            System.out.println(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
