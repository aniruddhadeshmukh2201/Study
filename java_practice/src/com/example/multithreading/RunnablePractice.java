package src.com.example.multithreading;

public class RunnablePractice {

    public static class Task implements Runnable {

        public String s;
        public Task(String s) {
            this.s = s;
        }

        @Override
        public void run() {
            System.out.println("from runnable task " + s);
        }

    }

    public static void main(String[] args) {
        Thread t = new Thread(new Task("Aniruddha"));

        t.start();
    }
}
