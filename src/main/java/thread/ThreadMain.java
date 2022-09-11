package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(123);
            }
        }).start();

        new Thread() {
            @Override
            public void run() {
                System.out.println(123);
            }
        }.start();

        FutureTask<Integer> callTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        });
        new Thread(callTask).start();
        callTask.get();


    }
}
