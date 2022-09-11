package intview.tb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangeMain {


    public static void main(String[] args) {
        Container container = new Container();


        Exchanger<Integer> exchanger = new Exchanger();
        Thread addThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    container.add(new Object());
                    System.out.println("add " + (i + 1));
                    if (container.size() == 5) {
                        try {
                            exchanger.exchange(container.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });

        Thread monitorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer size = 0;
                while (true) {
                    try {
                        System.out.println("monitor:" + exchanger.exchange(0));
                        break;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("over");
            }
        });

        addThread.start();
        monitorThread.start();
    }

    static class Container {
        //        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        List<Object> list = new ArrayList<>();

        public void add(Object o) {
            list.add(o);

        }

        public int size() {
            return list.size();
        }
    }


}

