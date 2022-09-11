package gc;

import thread.SleepHelper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolMain {

    public static void main(String[] args) {


        ThreadPoolExecutor pool = new ThreadPoolExecutor(20, 20,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1_0000_0000));
        for (; ; ) {
            pool.submit(getSleepTask());
        }


    }

    private static Runnable getSleepTask() {
        return new Runnable() {
            @Override
            public void run() {
                SleepHelper.sleepSeconds(3);
                Person person = new Person();
            }
        };
    }

    static class Person {
        Object object;
    }
}
