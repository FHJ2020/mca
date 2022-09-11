package singleton;

/**
 * 饿汉式
 * 1.构造方法私有
 * 2.暴露一个静态的方法或字段返回实例
 * 3.实例在class被加载到classLoader的时候被new出来，jvm保证线程安全
 * 4.一个classLoader，一个实例。类被classLoader加载的时候才会被使用
 *
 * 问题：
 *  只要类被加载，就会被实例化。
 *  类什么时候会被触发加载
 *  final 语义:编译的时候只让你赋值一次这个对象
 *
 *  Class.forName("")
 * 反射可以修改构造函数修饰符
 *
 *
 *
 * 懒汉式
 * getInstance 的时候才去创建对象，会有创建多个对象的问题，加锁防止重复创建
 *
 *
 */
public class EagerSingleton {

    /**
     *
     */
    private static  final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {

    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(EagerSingleton.getInstance());

//        Singleton01.class.getField("");
    }
}
