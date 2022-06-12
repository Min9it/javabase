package exer.minshik.runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executor
 */
public class ConcurrentExecutors {
    public static void main(String[] args) {

        /**
         * Single Thread 생성
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
        });


        /**
         * Multi Thread 생성(Param값 갯수만큼)
         */
        ExecutorService executorServicePool = Executors.newFixedThreadPool(2);

        executorServicePool.submit(getRunnable("fst"));
        executorServicePool.submit(getRunnable("snd"));
        executorServicePool.submit(getRunnable("trd"));
        executorServicePool.submit(getRunnable("fth"));

        /**
         * Scheduled Thread 생성
         */
        ScheduledExecutorService executorServiceScheduled = Executors.newSingleThreadScheduledExecutor();
        executorServiceScheduled.scheduleAtFixedRate(getRunnable("Scheduled"), 1, 2, TimeUnit.SECONDS);

        // executorService 생성 Thread Pool은 명시적으로 shutdown 필요

        // graceful shutdown
        executorService.shutdown();
        executorServicePool.shutdown();

        // force shutdown
//        executorService.shutdownNow();
    }

    private static Runnable getRunnable(String msg) {
        return () -> System.out.println(msg + Thread.currentThread().getName());
    }
}
