package exer.minshik.runnable;

public class ConcurrentAppSleep {
    public static void main(String[] args) {
        /**
         * 2. Thread Sleep
         */
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                //sleep중인 Thread를 깨우면(Interupt)
//                throw new RuntimeException(e);
                e.printStackTrace();
            }
            System.out.println("Thread : " + Thread.currentThread().getName());
        });

        thread.start();

        System.out.println("Main : " + Thread.currentThread().getName());
    }
}
