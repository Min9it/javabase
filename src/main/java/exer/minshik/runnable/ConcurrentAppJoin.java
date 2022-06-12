package exer.minshik.runnable;

public class ConcurrentAppJoin {
    public static void main(String[] args) {
        /**
         * Thread Join
         */
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();

        System.out.println("MainThread : " + Thread.currentThread().getName());
        try {
            //target Thread의 종료를 기다린다.
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(thread + "is Finished");
    }
}
