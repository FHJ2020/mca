package lock;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.layouters.Layouter;
import thread.SleepHelper;

public class LockInHeaderMain {

    public static void main(String[] args) {

        Object o1=new Object();
        Object o2=new Object();

        System.out.println("new:");
        System.out.println(ClassLayout.parseInstance(o1).toPrintable());
        synchronized (o1){
            System.out.println("main thread :");
            System.out.println(ClassLayout.parseInstance(o1).toPrintable());
        }
//        System.gc();
//        SleepHelper.sleepSeconds(5);
//        System.out.println("after gc");
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o1){
                         System.out.println("t1: ");
                    System.out.println(ClassLayout.parseInstance(o1).toPrintable());
                    SleepHelper.sleepSeconds(2);
                    System.out.println("t2 sync: ");
                    System.out.println(ClassLayout.parseInstance(o1).toPrintable());

                    synchronized (o2){
                        System.out.println(ClassLayout.parseInstance(o1).toPrintable());
                    }
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o2){
                    SleepHelper.sleepSeconds(1);
                    synchronized (o1){
                        System.out.println(ClassLayout.parseInstance(o1).toPrintable());
                    }
                }
            }
        });
        t1.start();
        t2.start();

    }
}
