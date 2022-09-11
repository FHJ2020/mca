package classLoader;

public class ClassLoader01 extends ClassLoader{

    public ClassLoader01(ClassLoader parent) {
        super(parent);
    }

    public ClassLoader01() {
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
