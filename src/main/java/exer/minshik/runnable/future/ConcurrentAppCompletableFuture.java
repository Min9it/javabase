package exer.minshik.runnable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentAppCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("ExecutorService is Running");
        });
//        Future<String> submit = executorService.submit(() -> "Hello");

        /**
         * CompletableFuture생성
         */
        // 1. 명시적으로 complete 메서드 호출
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("MS");

        //2. Static Factory 메서드 호출
       CompletableFuture<String> futureUsingFactory = CompletableFuture.completedFuture("MS");

        /**
         * runAsync   : return이 없는경우
         * supplyAsync: return이 있는 경우
         *  - thenApply : return값을 받아 처리하는 경우(callback) -> return 있음
         *  - thenAccept: return값을 받아 처리하는 경우(callback) -> return 없음
         *  - thenRun   : return값 없이 작업완료 시 실행
         */
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync is Executed");
//            System.out.println("hello" + Thread.currentThread().getName());
        });

        CompletableFuture<String> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync is Executed");
            return "Hello";
        }).thenApply(s -> s.toUpperCase()); //has return


        CompletableFuture<Void> spplyAsyncFutureThenAccept = CompletableFuture.supplyAsync(() -> {
//            System.out.println("supplyAsync is Executed");
            return "Hello";
        }).thenAccept(s -> {
            System.out.println("spplyAsyncFutureThenAccept result(NOT RETURN) : " + s.toLowerCase());
        });

        CompletableFuture<Void> spplyAsyncFutureThenRun = CompletableFuture.supplyAsync(() -> "Hello").thenRun(() ->
                System.out.println(Thread.currentThread().getName()));

        CompletableFuture<Void> spplyAsyncFutureThenRun2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "Hello";
        }, executorService /* 명시적으로 thread pool 사용 선언*/).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        System.out.println("future = " + future.get());
        System.out.println("futureUsingFactory = " + futureUsingFactory.get());
        System.out.println("supplyAsyncFuture = "  + supplyAsyncFuture.get());                  // return 있음
        System.out.println("spplyAsyncFutureThenAccept = " + spplyAsyncFutureThenAccept.get()); // return 없음
        System.out.println("spplyAsyncFutureThenRun = " + spplyAsyncFutureThenRun.get());
        System.out.println("spplyAsyncFutureThenRun2 = " + spplyAsyncFutureThenRun2.get());

        System.out.println(future.isDone());
        System.out.println(futureUsingFactory.isDone());
        System.out.println(supplyAsyncFuture.isDone());
        System.out.println(spplyAsyncFutureThenAccept.isDone());
        System.out.println(spplyAsyncFutureThenRun.isDone());
        System.out.println(spplyAsyncFutureThenRun2.isDone());

        executorService.shutdown();
    }
}
