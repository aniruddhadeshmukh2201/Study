package src.com.example.multithreading;

public class ThreadPractice {

    public static class Task extends Thread {
        @Override
        public void run() {
            System.out.println("running using threaed class... " + this.getName());
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("running from the lambda and the thread obj : " + Thread.currentThread().getName()));
        t1.start();
        Task t = new Task();
        t.start();
        System.out.println("----this is the main thread : " + Thread.currentThread().getName());
    }
}
