package exer.minshik.runnable;

public class ConcurrentAppMakeThread {
    public static void main(String[] args) {

        /**
         * 1. Thread 생성
         */
        // Main Thread
        System.out.println("1. MainThread: " + Thread.currentThread().getName());

        // Thread 클래스를 상속
       FstThread FstThread = new FstThread();

        // Java 1.8 미만
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2. RunnableThread : " + Thread.currentThread().getName());
            }
        });

        // Java 1.8 미만(Lambda Expression 사용)
        Thread lambdaThread = new Thread(() -> {
            System.out.println("3. LambdaThread : " + Thread.currentThread().getName());
        });

        FstThread.start();
        thread.start();
        lambdaThread.start();
    }
    static class FstThread extends Thread {
        public void run() {
            System.out.println("4. FstThread : " + Thread.currentThread().getName());
        }
    }

    // Thread는 실행순서를 보장하지 않는다.
}
