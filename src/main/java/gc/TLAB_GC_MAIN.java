package gc;

public class TLAB_GC_MAIN {
    public static void main(String[] args) {

        while (true) {
            Object o = new Object();
            fun(o);
        }
    }

    public static void fun(Object o) {
        if (o.hashCode() > 1111111) {

        }
    }
}
