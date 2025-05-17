package src.com.example.multithreading;

public class LambdaPractice {
    static Runnable r = () -> System.out.println("hello");

    static class Task implements Runnable {
        
        @Override
        public void run() {
            System.out.println("from the inner class");
        } 
    }
    public static void main(String[] args) {
        r.run();

        new Task().run();

    }
}
