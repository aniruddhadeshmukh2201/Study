package com.example.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CustomThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final List<Thread> workers;
    private final int capacity;

    public CustomThreadPool(int capacity) {
        this.capacity = capacity;
        taskQueue = new ArrayBlockingQueue<>(this.capacity);
        workers = new ArrayList<>();

        for(int i = 0; i < capacity; i++ ) {
            
            Thread worker = new Thread(() -> {
                try {
                    while(true) {
                        Runnable r = taskQueue.take();
                        r.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); 
                }
            });
            worker.start();
            workers.add(worker);
        }
    }

    public void submit(Runnable task) throws InterruptedException {
        taskQueue.put(task);
    }


    public void shutdown() {
        for(Thread worker : workers) {
            worker.interrupt();
        }
    }

}
