package gc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GcMain {

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        for(int i = 0; i < 5_0000_000; i++){
            createObject();
        }
        System.out.println("cost = " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void createObject(){
        synchronized (new Object()){

        }
    }
}
