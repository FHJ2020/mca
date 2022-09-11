package container;

import java.util.PriorityQueue;

public class PriorityQueueMain {

    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");
        queue.add("1");
        queue.add("2");


        while (queue.size() > 0) {
            System.out.println(queue.poll());
        }
       /* String s;
        while (( s = queue.poll()) != null) {
            System.out.println(s);
        }*/
    }
}
