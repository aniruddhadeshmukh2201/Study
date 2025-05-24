package com.example.multithreading;

import java.util.Timer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueuePractice {
    public static void main(String[] args) {
        BlockingQueue<String> b = new ArrayBlockingQueue<>(5);
        
        // producer
        new Thread(() -> {
            for(int i = 0; i < 100; i++ ) {
                try {
                    b.put("a" + i);
                    System.out.println("Produced: " + "a" + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        // consumer
        new Thread(() -> {
            try {
                for(int i = 0; i < 100; i++ ) {
                    System.out.println("consumed: " + b.take());
                    Thread.sleep(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
