package liao.kafka.controller;

import java.lang.reflect.Method;

public class ForTest {

    public static void main(String[] args) throws Exception {
        Class<?> clz = Class.forName("A");
        Object o = clz.newInstance();
        Method m = clz.getDeclaredMethod("hello", null);
        m.invoke(o);
    }
    static class A{
        public void hello() {
            System.out.println("hello world");
        }
    }


}

 class TestClassLoad {
//    public static void main(String[] args) throws Exception {
//        Class<?> clz = Class.forName(A.class.getName());
//        Object o = clz.newInstance();
//        Method m = clz.getDeclaredMethod("hello", null);
//        m.invoke(o);
//    }
//     class A{
//        public void hello() {
//            System.out.println("hello world");
//        }
//    }
}
