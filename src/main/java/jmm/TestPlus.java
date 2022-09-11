package jmm;

public class TestPlus {
    static int p = 20;
    int t = 1;

    public static void main(String[] args) {
        int i = 5;
//        i=i++;
        i = ++i;
        System.out.println(i);
    }

    public static void main() {
        int i = 5;
//        i++;
        ++i;
        System.out.println(i);
    }

    public static void test() {
        int a = 1;
        long e = 0x8fffffff;
        System.out.println(a + 1);
    }

    public long test2() {
        for (int i = 0; i < 10; i++) {
            Object o1 = new Object();
            System.out.println(i);
        }
        for (int j = 0; j < 10; j++) {
            Object o2 = new Object();
            System.out.println(j);
        }
        long b = Long.MAX_VALUE;
        b++;
        return b;
    }

    public void test3() {
        long c = test2();
    }

    public void test4() {
        int a = 10;
        int b = 20;
        int c = a + b;
    }
}
