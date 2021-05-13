package liao.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@Component
public class TestConcurrentHashMap {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConcurrentHashMap.class);

//    public static void main(String[] args) {
//        //使用只能5个有限队列，corePoolSize=2, maxPoolSize=10
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10,
//                0L, TimeUnit.MILLISECONDS,
//                new ArrayBlockingQueue<>(5));
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
//
//        //创建15个线程
//        for (int i = 0; i < 15; i++) {
//            final int a = i;
//            executor.submit(() -> {
//                try {
//                    Thread.sleep(500);
//                    System.out.println(a + "   --- " + executor.getQueue().size());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        executor.shutdown();
//    }

    private Map<String, String> concurrentHashMap = new ConcurrentHashMap();
    private List<String> list2 = new ArrayList<>();

    @Scheduled(cron = "0/30 * *  * * ? ")   //每5秒执行一次
    public void execute() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
//        System.out.println("开始执行定时任务 " + df.format(new Date()));
        int threadNum = 10;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum, threadNum,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(20));
        try {
            LOGGER.info("------start----------");
            for (int i = 0; i < threadNum; i++) {
                int index = i;
                executor.execute(() -> {
//                    LOGGER.info("------start:(" + index + ")----------");
                    doTask1(index);
                });
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        } finally {
            executor.shutdown();
            LOGGER.info("------end----------");
        }
    }

    private void doTask1(int index) {
        List<String> list = Arrays.asList("ces1", "ces2", "ces3", "ces4", "ces5", "ces6", "ces7", "ces8", "ces9", "ces10");
        list.forEach(p -> {
            if (!concurrentHashMap.containsKey(p)) {
//                concurrentHashMap.put(p, String.valueOf(index));
                list2.add(p);
                concurrentHashMap.put(p, "");
            }
        });
        synchronized (this) {
            list2.forEach(p -> System.out.print(p + ","));
            System.out.print("\n");
        }
    }

//    @Scheduled(cron = "0/30 * *  * * ? ")   //每5秒执行一次
//    public void execute2() {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
////        System.out.println("开始执行定时任务 " + df.format(new Date()));
//        int threadNum = 10;
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum, threadNum,
//                0L, TimeUnit.MILLISECONDS,
//                new ArrayBlockingQueue<>(20));
//        try {
//            LOGGER.info("------start----------");
//            for (int i = 0; i < threadNum; i++) {
//                int index = i;
//                executor.execute(() -> {
////                    LOGGER.info("------start:(" + index + ")----------");
//                    doTask2(index);
//                });
//            }
//        } catch (Exception e) {
//            LOGGER.error("", e);
//        } finally {
//            executor.shutdown();
//            LOGGER.info("------end----------");
//        }
//    }

//    private void doTask2(int index) {
//        List<String> list = Arrays.asList("ces1", "ces2", "ces3", "ces4", "ces5", "ces6", "ces7", "ces8", "ces9", "ces10");
//        list.forEach(p -> {
//            if (!concurrentHashMap.containsKey(p)) {
////                concurrentHashMap.put(p, String.valueOf(index));
//                list2.add(p);
//                concurrentHashMap.put(p, "");
//            }
//        });
//        synchronized (this) {
//            list2.forEach(p -> System.out.print(p + ","));
//            System.out.print("\n");
//        }
//    }


}
