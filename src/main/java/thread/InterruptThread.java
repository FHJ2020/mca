package thread;

public class InterruptThread {

    public static void main(String[] args) {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                SleepHelper.sleepSeconds(10);
                System.out.println("t1 over");
            }
        });
        t1.start();
        SleepHelper.sleepSeconds(2);
        t1.interrupt();
    }
}
