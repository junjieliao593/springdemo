package liao.concurrent;

public class MyThreadPoolExecutor2 implements AbstractExecutorService {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("MyThreadPoolExecutor2-----------------" + i);
        }
    }
}
