package classLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ClassLoader02 extends ClassLoader {


    public ClassLoader02() {
        super(null);
    }

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

    public void cDefineClass(String name, byte[] b, int off, int len) {
        defineClass(name, b, off, len);
    }


}
