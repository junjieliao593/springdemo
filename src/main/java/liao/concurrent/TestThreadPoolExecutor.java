package liao.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ljj
 */
public class TestThreadPoolExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 2,
                100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
//        Future<String> future1 = (Future<String>) tpe.submit(new MyThreadPoolExecutor1());
//        Future<String> future2 = (Future<String>) tpe.submit(new MyThreadPoolExecutor2());
//        Future<String> future3 = (Future<String>) tpe.submit(new MyThreadPoolExecutor3());
//        Future<String> future1_1 = (Future<String>) tpe.submit(new MyThreadPoolExecutor1());
//        Future<String> future1_2 = (Future<String>) tpe.submit(new MyThreadPoolExecutor1());


        tpe.execute(new MyThreadPoolExecutor1());
        tpe.execute(new MyThreadPoolExecutor2());
        tpe.execute(new MyThreadPoolExecutor3());

    }
}
