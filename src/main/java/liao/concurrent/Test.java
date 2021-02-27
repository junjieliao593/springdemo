package liao.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {

        //使用只能5个有限队列，corePoolSize=2, maxPoolSize=10
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        //创建15个线程
        for (int i = 0; i < 15; i++) {
            final int a = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println(a + "   --- " + executor.getQueue().size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

}
