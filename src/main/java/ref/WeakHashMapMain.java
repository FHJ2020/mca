package ref;

import java.util.WeakHashMap;

public class WeakHashMapMain {

    public static void main(String[] args) {
        WeakHashMap weakHashMap = new WeakHashMap();
        Value v2 = null;

        for (int i = 1; i < 11; i++) {
            System.out.println("before create size:" + weakHashMap.size());

            Value temp  = new Value(i);

//            System.out.println(v2);

            weakHashMap.put(new Key(i), temp);
            System.out.println("after create size:" + weakHashMap.size());

//            v2 = temp;

        }

    }

    static class Key {
        private int id;

        public Key(int id) {
            this.id = id;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize key id :" + id);
        }
    }

    static class Value {
        private int id;
        byte[] bytes = new byte[10 * 1024 * 1024];

        public Value(int id) {
            this.id = id;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize Value id :" + id + " bytes:" + bytes);
        }
    }
}
