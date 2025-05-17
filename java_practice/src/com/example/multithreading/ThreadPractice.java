package src.com.example.multithreading;

public class ThreadPractice {

    public static class Task extends Thread {
        @Override
        public void run() {
            System.out.println("running using threaed class... " + this.getName());
        }
    }

    public static void main(String[] args) {
        try {
            Thread t1 = new Thread(() -> {
            try {
                // System.out.println("status 3: " + Thread.currentThread().getState());
                Thread.sleep(5000);
            }catch(Exception e) {
                System.err.println("exception occured...");
            }
        });
        System.out.println("status 1: " + t1.getState());
        t1.start();
        System.out.println("status 2: " + t1.getState());
        Thread.sleep(100);
        System.out.println("status 4: " + t1.getState());
        t1.join();
        System.out.println("status 5: " + t1.getState());
    } catch(Exception e) {
        System.err.println("exception in main");
    }

    // for waiting and blocked, we need to get into 
        
        // Task t = new Task();
        // t.start();
        // System.out.println("----this is the main thread : " + Thread.currentThread().getName());
    }
}
