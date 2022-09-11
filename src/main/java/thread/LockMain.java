package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockMain {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                sleep(5);
                lock.unlock();
            }
        });
        t1.start();
        sleep(1);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                    LockSupport.park();
//                    lock.tryLock(6,TimeUnit.SECONDS);


//                lock.unlock();
            }
        });
        t2.start();
        sleep(1);

        System.out.println(t2.getState());
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
