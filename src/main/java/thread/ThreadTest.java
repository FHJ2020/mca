package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    public static void main(String[] args) {
        ExecutorService s = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            s.submit(new Runnable() {
                @Override
                public void run() {
                    SleepHelper.sleepSeconds(1);
                }
            });
        }

    }
}
