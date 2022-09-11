package classLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RedefineClassMain {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader02 classLoader02=new ClassLoader02();
        classLoader02.loadClass("");
//        classLoader02.cDefineClass();
//        classLoader02.re
    }

    /*static class Cl extends ClassLoader{
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            String dir = "F:\\workspace\\demo-all\\mca\\target\\classes\\";
            String path = name.replace('.', '/').concat(".class");
            try {
                FileInputStream fileInputStream = new FileInputStream(dir + path);
                byte[] bytes = new byte[fileInputStream.available()];
                fileInputStream.read(bytes);
                defineClass(name, bytes, 0, bytes.length);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return super.loadClass(name);
        }
    }*/
}
