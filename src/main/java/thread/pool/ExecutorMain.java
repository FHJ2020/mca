package thread.pool;

import java.util.concurrent.*;

public class ExecutorMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);


        Future<?> future1 = pool.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        // null
        System.out.println(future1.get());

        Future<Object> future2 = pool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return 100;
            }
        });
        // 100
        System.out.println(future2.get());

        FutureTask<Object> futureTask1 = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return 200;
            }
        });
        pool.submit(futureTask1);
        // 200
        System.out.println(futureTask1.get());

        FutureTask<Object> futureTask2 = new FutureTask<Object>(new Runnable() {
            @Override
            public void run() {

            }
        },300);
        pool.submit(futureTask2);
        // 300
        System.out.println(futureTask2.get());



        FutureTask<Object> futureTask3 = new FutureTask<Object>(new Runnable() {
            @Override
            public void run() {

            }
        },400);
        pool.execute(futureTask3);
        // 400
        System.out.println(futureTask3.get());

        FutureTask<Object> futureTask4 = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return 500;
            }
        });
        pool.submit(futureTask4);
        // 500
        System.out.println(futureTask4.get());

        System.out.println("thread================");
        new Thread(futureTask2).start();
        System.out.println(futureTask1.get());

        new Thread(futureTask2).start();
        System.out.println(futureTask2.get());

    }
}
