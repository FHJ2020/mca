package ref;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WeakReferenceMain {

    public static void main(String[] args) {
        List<WeakCache> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new WeakCache(new CacheReferent(), new CacheNoReferent()));
        }

        for (int i = 0; i < list.size(); i++) {
            WeakCache weakCache = list.get(i);
            if (weakCache.CacheNoReferent == null) {
                System.out.println("111111");
            }
            if (weakCache.get() == null) {
                System.out.println("222222");

            }
        }
    }

    static class WeakCache<T> extends WeakReference<T> {

        private Object CacheNoReferent;

        public WeakCache(T referent) {
            super(referent);
        }

        public WeakCache(T referent, Object CacheNoReferent) {
            super(referent);
            this.CacheNoReferent = CacheNoReferent;
        }

    }

    static class CacheReferent {
        byte[] padding = new byte[1 * 1204 * 1024];

        @Override
        protected void finalize() throws Throwable {
            System.out.println("CacheReferent  finalize" + padding);
        }
    }

    static class CacheNoReferent {
        byte[] padding = new byte[1 * 1204 * 1024];

        @Override
        protected void finalize() throws Throwable {
            System.out.println("CacheNoReferent  finalize " + padding);
        }
    }
}
