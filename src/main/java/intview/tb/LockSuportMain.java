package intview.tb;

import thread.SleepHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class LockSuportMain {
    public static volatile List<Object> list = new ArrayList<>();

    public static void add(Object o) {
        list.add(o);
    }

    public static int size() {
        return list.size();
    }

    public static void main(String[] args) {


        AddThread addThread = new AddThread();
        MonitorThread monitorThread = new MonitorThread();
        addThread.setMonitorThread(monitorThread);
        monitorThread.setAddThread(addThread);


        monitorThread.start();
        addThread.start();
    }


    static class MonitorThread extends Thread {
        Thread addThread;

        public Thread getAddThread() {
            return addThread;
        }

        public void setAddThread(Thread addThread) {
            this.addThread = addThread;
        }

        @Override
        public void run() {
            LockSupport.park();
            System.out.println("over");
            LockSupport.unpark(addThread);
        }
    }

    static class AddThread extends Thread {

        Thread monitorThread;

        public Thread getMonitorThread() {
            return monitorThread;
        }

        public void setMonitorThread(Thread monitorThread) {
            this.monitorThread = monitorThread;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                add(new Object());
                System.out.println("add " + (i + 1));
                if (size() == 5) {
                    LockSupport.unpark(monitorThread);
                    LockSupport.park();
                }
//                    SleepHelper.sleepSeconds(1);
            }
        }
    }
}
