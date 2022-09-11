package thread;

import thread.three.OrderingF;

public class OrderingS extends OrderingF {
    private int x = 10;


    public OrderingS() {
        new Thread(() -> System.out.println(this.x)).start();
    }

    public static void main(String[] args) {
        OrderingS orderingS = new OrderingS();

    }
}
