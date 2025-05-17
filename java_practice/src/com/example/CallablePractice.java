package src.com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallablePractice {
    public static class Task implements Callable<String> {

        public String s;
        public Task(String s) {
            this.s = s;
        }


        @Override
        public String call() {
            return "Hello " + s;
        }
    }


    public static void main(String[] args) {
        ExecutorService e = Executors.newSingleThreadExecutor();
        Future<String> f = e.submit(new Task("Aniruddha"));

        try {
            String res = f.get();
            System.out.println("---future---" + res);
        } catch(Exception er) {
            er.printStackTrace();
        } finally {
            e.shutdown();
        }
    }
}
