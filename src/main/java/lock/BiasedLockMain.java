package lock;

import org.openjdk.jol.info.ClassLayout;
import thread.SleepHelper;

public class BiasedLockMain {

    public static volatile int a=10;

    public static void main(String[] args) {


//        System.out.println();
        Object o1=new Object();
        System.out.println("o1:");
        System.out.println(ClassLayout.parseInstance(o1).toPrintable());

        SleepHelper.sleepSeconds(2);
        System.out.println("o1 after 2s:");
        System.out.println(ClassLayout.parseInstance(o1).toPrintable());

        synchronized (o1){
            System.out.println("o1 synchronized:");
            System.out.println(ClassLayout.parseInstance(o1).toPrintable());
        }


        Object o2=new Object();
        System.out.println("o2:");
        System.out.println(ClassLayout.parseInstance(o2).toPrintable());

        synchronized (o2){
            System.out.println("o2 synchronized:");
            System.out.println(ClassLayout.parseInstance(o2).toPrintable());
        }

        System.out.println("o2 after synchronized :");
        System.out.println(ClassLayout.parseInstance(o2).toPrintable());

        SleepHelper.sleepSeconds(60);

        System.out.println("o2 after 60s :");
        System.out.println(ClassLayout.parseInstance(o2).toPrintable());
    }
}
