package thread.pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestArray {

    public static void main(String[] args) {
//        Integer[] ints = new Integer[1000];
//        List<Integer> ints = new ArrayList<>(1200);
        List<Integer> ints = new LinkedList();
        int listNums = 1000;
        int loop = 500_0000;
        for (int i = 0; i < listNums; i++) {
            ints.add(i);
//            ints[i] = i;
        }
        long start = System.currentTimeMillis();

        for (int i = 0; i < loop; i++) {
            for (Integer listInteger : ints) {
                methodI(listInteger);
            }
        }
        System.out.println(System.currentTimeMillis() - start);


    }

    public static void methodI(Integer i) {
        if (i.equals(1) || i.equals(500)) {
//            System.out.println();
        } else {

        }
    }
}
