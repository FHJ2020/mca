package intview.tb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WithOutVolatileMain {

    public static volatile   List<Object> list = Collections.synchronizedList(new ArrayList<>());

    public static void add(Object o) {
        list.add(o);
    }

    public static int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Thread addThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    list.add(new Object());
                    System.out.println("add " + (i + 1));
//                    SleepHelper.sleepSeconds(1);
                }
            }
        });
        Thread monitorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (list.size() == 5) {
                        System.out.println("over");
                        break;
                    }
                }
            }
        });

        addThread.start();
        monitorThread.start();
    }


}
