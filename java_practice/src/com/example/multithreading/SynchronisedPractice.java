package com.example.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronisedPractice {

    private static int count1 = 0;
    private static int count2 = 0;

    public static synchronized void syncIncrement() {
        count1++;
    }

    public static void increment() {
        count2++;
    }

    public static int getCount1() {
        return count1;
    }

    public static int getCount2() {
        return count2;
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10000; i++) {

            executor.submit(() -> {
                increment();
            });
        }

        for (int i = 0; i < 10000; i++) {
            executor.submit(() -> {
                syncIncrement();
            });
        }
        executor.shutdown();

        try {
            // Wait up to 5 seconds for all tasks to finish
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("normal increment : " + getCount2());
        System.out.println("sync increment : " + getCount1());

    }
}
