package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ThreadNum {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        double[] num = new double[1_0000_0000];
        Random random = new Random();
        double result = 0;
        for (int i = 0; i < num.length; i++) {
            num[i] = random.nextDouble();
            result += num[i];
        }
        System.out.println(result);


        computeTest(num, 1);
        computeTest(num, 32);
        computeTest(num, 64);
        computeTest(num, 16);

    }

    private  static void computeTest(double[] num, int threadNum) {
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        int segmentNums = threadNum;
        int segmentSize = num.length / segmentNums;
        double[] results = new double[segmentNums];

        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= segmentNums; i++) {
            int start = (i - 1) * segmentSize;
            int end = i * segmentSize;

            int segmentIndex = i - 1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = start; j < end; j++) {
                        results[segmentIndex] += num[j];
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        double threadResult = 0;
        for (double r : results) {
            threadResult += r;
        }
        System.out.println("time: " + (endTime - startTime) + " " + threadResult);

    }
}
