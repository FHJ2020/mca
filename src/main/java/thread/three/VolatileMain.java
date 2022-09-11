package thread.three;

public class VolatileMain {

    private static volatile int num=10;
    public static void main(String[] args) {
        System.out.println(num);
    }
}
