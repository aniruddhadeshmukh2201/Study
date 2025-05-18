package com.example.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServicePractice {

    static ExecutorService executor = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++ ) {
            int taskNumber = i;
            executor.submit(() -> {
                System.out.println("printing task no. " + taskNumber + " : " + Thread.currentThread().getName());
            });
        }


        executor.shutdown();
    }
}
