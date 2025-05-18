package com.example.multithreading;

public class ThreadPractice {

    public static class Task extends Thread {
        @Override
        public void run() {
            System.out.println("running using threaed class... " + this.getName());
        }
    }

    public static void main(String[] args) {
        // try {
        // Thread t1 = new Thread(() -> {
        // try {
        // // System.out.println("status 3: " + Thread.currentThread().getState());
        // Thread.sleep(5000);
        // } catch (Exception e) {
        // System.err.println("exception occured...");
        // }
        // });
        // System.out.println("status 1: " + t1.getState());
        // t1.start();
        // System.out.println("status 2: " + t1.getState());
        // Thread.sleep(100);
        // System.out.println("status 4: " + t1.getState());
        // t1.join();
        // System.out.println("status 5: " + t1.getState());
        // } catch (Exception e) {
        // System.err.println("exception in main");
        // }
        // for waiting and blocked, we need to get into

        // Task t = new Task();
        // t.start();
        // System.out.println("----this is the main thread : " +
        // Thread.currentThread().getName());

        // simulating waiting status
        // try {
        // Thread t1 = new Thread(() -> {
        // try {
        // Thread.sleep(2000);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // });

        // Thread t2 = new Thread(() -> {
        // try {
        // t1.join();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // });
        // t1.start();
        // t2.start();
        // Thread.sleep(1000);
        // System.out.println("status : " + t2.getState());
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // try {
        // Object lock = new Object();
        // Thread t1 = new Thread(() -> {

        // synchronized (lock) {
        // try {
        // lock.wait();
        // } catch (InterruptedException e) {

        // }
        // }
        // });
        // t1.start();
        // Thread.sleep(1000);
        // System.out.println("Status : " + t1.getState());
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // simulating blocked state

        // try {

        // Object lock = new Object();

        // Thread t1 = new Thread(()-> {
        // synchronized (lock) {
        // try {
        // while(true);
        // } catch(Exception e) {}
        // }
        // });
        // Thread t2 = new Thread(() -> {
        // synchronized (lock) {
        // try {
        // System.out.println("t2 acquired the lock...");
        // } catch(Exception e) {}
        // }
        // });

        // t1.start();
        // Thread.sleep(1000);
        // t2.start();
        // Thread.sleep(1000);

        // System.out.println("Status of t2 : " + t2.getState());
        // } catch(Exception e) {
        // e.printStackTrace();
        // }

        // race condition

        int[] val = new int[]{0};
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 100000; i++ ) {
                val[0]++;
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 100000; i++ ) {
                val[0]--;
            }
        });

        t1.start();
        t2.start();

        

        try {
            Thread.sleep(2000);
            t1.join();
            t2.join();
        } catch (Exception e) {

        }

        System.out.println(val[0]);

    }
}
