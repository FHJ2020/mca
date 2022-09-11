package gc;

import java.util.concurrent.*;

public class CodeTruningMain {

    public static void main(String[] args) {
        long num = 100_0000_0000_0000L;

        Person o = new Person();

        for (long i = 0; i < num; i++) {
//            Person o = new Person();
            o = new Person();
        }


    }

    static class Person {
        Object object;
    }
}
