package classLoader;

public class ClassLoaderMain {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
/*        System.out.println("load from app");
        Class<?> aClass = ClassLoaderMain.class.getClassLoader().loadClass("classLoader.TestClass");
        System.out.println(aClass.getName());
        aClass.newInstance();*/

/*//        ClassLoader01.getSystemClassLoader();
        ClassLoader01 classLoader01=new ClassLoader01();
//        System.out.println(ClassLoader01.class.getClassLoader());
//        System.out.println(ClassLoader01.class.getClassLoader().getParent());

        System.out.println(classLoader01.getClass().getClassLoader());
        System.out.println(classLoader01.getParent());*/


        ClassLoader02 classLoader02=new ClassLoader02();
        Class<?> defaultClass = classLoader02.loadClass("classLoader.DefaultClass");
        System.out.println(defaultClass);
        System.out.println(defaultClass.newInstance());
        System.out.println(defaultClass.getSuperclass());
        System.out.println(defaultClass.getSuperclass().getClassLoader());

        System.out.println("--------");
        Class<?> sonClass = classLoader02.loadClass("classLoader.SonClass");
        System.out.println(sonClass);
        System.out.println(sonClass.newInstance());
        System.out.println(sonClass.getSuperclass());
        System.out.println(sonClass.getSuperclass().getClassLoader());
    }
}
