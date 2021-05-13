package liao.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap2 {

    private static Map<Long, Integer> widgetCacheMap = new ConcurrentHashMap<Long, Integer>();

    /**
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread tt = new Thread(new Rund());
            tt.start();
        }
    }

    static class Rund implements Runnable {

        public void run() {
            for (int i = 0; i < 100; i++) {
                test();
            }
        }


        /**
         * 1W次，总有那么几次线程不安全
         */
        public void test() {
//            synchronized ("")
            {// 解决方案
//                TestConcurrentHashMap2 tt = new TestConcurrentHashMap2();
//                synchronized(""){
                widgetCacheMap.put(1L, 1);
                int s1 = widgetCacheMap.get(1L).intValue();
                widgetCacheMap.put(1L, 2);
                int s2 = widgetCacheMap.get(1L).intValue();
                if (s1 == s2) {
                    System.out.println(s1 + ":" + s2);
                }
//                }
            }
        }

    }

    public synchronized void set() {
//        Map<Long, Integer> mm = new HashMap<Long, Integer>();
        widgetCacheMap.put(1L, 1);
//        widgetCacheMap = mm;
    }

    public synchronized void change() {
//        Map<Long, Integer> mm = new HashMap<Long, Integer>();
        widgetCacheMap.put(1L, 2);
//        widgetCacheMap = mm;
    }

}
