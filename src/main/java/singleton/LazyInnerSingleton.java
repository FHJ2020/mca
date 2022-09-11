package singleton;

public class LazyInnerSingleton {

    /**
     * 1.静态内部类
     * 2.JVM保证单例
     * 3.加载外部类的时候，不会加载内部类
     *
     * 类加载机制
     */

    private LazyInnerSingleton() {

    }

    private static class Holder {
        private static LazyInnerSingleton INSTANCE = new LazyInnerSingleton();
    }



    public static LazyInnerSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(LazyInnerSingleton.getInstance());

//        Singleton01.class.getField("");
    }
}
