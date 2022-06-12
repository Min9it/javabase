package exer.minshik.runnable;

public class ConcurrentAppInterrupt {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Thread Interrupt
         */
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                    return;
                }
            }
        });
        thread.start();

        System.out.println("MainThread : " + Thread.currentThread().getName());

        Thread.sleep(3000L);
        thread.interrupt();


        thread.join();
        System.out.println(thread + "is Finished");
    }
}
