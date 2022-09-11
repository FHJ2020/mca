package jmm;

public class JitWatch {

    private static volatile int num = 10;
    private static volatile Object instance = null;

    public static Object getInstance() {
        if (instance == null) {
            instance = new Object();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(num);
        System.out.println(getInstance());

    }
}
