package classLoader;

public class CountMain {

    static class T {
        static T t = new T();

        static int count = 2;

        private T() {
            count++;
        }
    }

    public static void main(String[] args) {
        System.out.println(T.count);
    }
}
