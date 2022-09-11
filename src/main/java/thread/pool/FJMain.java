package thread.pool;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class FJMain {
    //    static volatile Long[] nums = new Long[20];

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        old(2000_0000);
        fjp(2000_0000);


    }

    private static void fjp(int arrayLength) {
        System.out.println("===========fjp start");
        long[] nums = new long[arrayLength];



//        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        InitRecursiveAction task = new InitRecursiveAction(nums, 0, nums.length);

        long start = System.currentTimeMillis();
        forkJoinPool.submit(task);
        task.fork();
        forkJoinPool.awaitQuiescence(10, TimeUnit.SECONDS);

        System.out.println("init cost " + (System.currentTimeMillis() - start));


        start = System.currentTimeMillis();
//        long sum = Arrays.stream(nums).parallel().reduce((aLong, aLong2) -> aLong + aLong2).getAsLong();
        long sum = Arrays.stream(nums).parallel().sum();

        System.out.println("sum: " + sum);
        System.out.println("calculate cost " + (System.currentTimeMillis() - start));

        System.out.println("===========fjp end");
    }

    public static void old(int arrayLength) {
        System.out.println("===========old start");

        long start = System.currentTimeMillis();

        long[] nums = new long[arrayLength];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        System.out.println("init cost " + (System.currentTimeMillis() - start));

         start = System.currentTimeMillis();

        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        System.out.println("calculate cost " + (System.currentTimeMillis() - start));

        System.out.println("sum: " + sum);
        System.out.println("===========old end");

    }

    static class InitRecursiveAction extends RecursiveAction {

        long[] nums;
        int start;
        int end;

        public InitRecursiveAction(long[] nums, int start, int end) {
            this.nums = nums;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= 50000) {
                for (int i = start; i < end; i++) {
//                    nums[i] = Long.valueOf(i);
                    nums[i] = i;
//                    SleepHelper.sleepSeconds(1);
                }
            } else {
                int middle = start + (end - start) / 2;
                InitRecursiveAction task1 = new InitRecursiveAction(nums, start, middle);
                InitRecursiveAction task2 = new InitRecursiveAction(nums, middle, end);
                task1.fork();
                task2.fork();
            }
        }
    }
}
