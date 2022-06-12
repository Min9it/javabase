package exer.minshik.runnable.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentAppFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Callable과 runnable은 return값 유무의 차이
        Callable<String> hello = () -> {
          Thread.sleep(2000L);
          return "Hello";
        };
        Callable<String> java = () -> {
          Thread.sleep(3000L);
          return "java";
        };
        Callable<String> spring = () -> {
          Thread.sleep(1000L);
          return "spring";
        };

        /**
         * executor.invokeAll 여러 작업(runnable, callable) 동시 실행한 작업 중 가장 오래 걸리는 작업 만큼 자원(시간) 소요
         * execuor.invokeAny  여러 작업(runnable, callable) 중 완료된 작업이 있으면 종료(blocking Call)
         */
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, spring));
        for (Future<String> item: futures) {
            System.out.println(item.get());
        }

        System.out.println("================================================");

        Future<String> helloFuture = executorService.submit(hello);

        System.out.println(helloFuture.isDone());
        System.out.println("Started");

        /**
         * submit.get    : submit이 return하는 값을 가져옴
         * submit.isDone : thread 종료 여부 return
         * submit.cancle(boolean) : cancle을 호출하는 순간 isDone = true로 변경되며 get메서드를 사용해 return을 가져올 수 없음
         *      true: interrupt하며 종료
         *      false: wait
         * get을 만난 순간 thread 결과값을 기다림(Blocking Call)
         */

//        helloFuture.get();
        helloFuture.cancel(true);
        System.out.println(helloFuture.isDone());
        System.out.println("End");
        executorService.shutdown();
    }
}
