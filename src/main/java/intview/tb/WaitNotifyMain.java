package intview.tb;

import java.util.ArrayList;
import java.util.List;

public class WaitNotifyMain {


    public static void main(String[] args) {
        Container container = new Container();


        Object lock = new Object();
        Thread addThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        container.add(new Object());
                        System.out.println("add " + (i + 1));
                        if (container.size() == 5) {
                            lock.notifyAll();
                            waitM(lock);
                        }
                    }
                }
            }
        });

        Thread monitorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if (container.size() == 5) {
                            System.out.println("over");
                            lock.notifyAll();
                            break;
                        } else {
                            lock.notifyAll();
                            waitM(lock);
                        }
                    }
                }
            }
        });

        addThread.start();
        monitorThread.start();
    }

    private static void waitM(Object lock) {
        try {
            lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Container {
        List<Object> list = new ArrayList<>();

        public void add(Object o) {
            list.add(o);
        }

        public int size() {
            return list.size();
        }
    }


}

