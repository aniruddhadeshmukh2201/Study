package com.example.multithreading;

import java.util.LinkedList;
import java.util.List;

public class CustomBlockingQueue {

    List<String> q = new LinkedList<>();
    private final int capacity;

    public CustomBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(String item) throws InterruptedException {
        synchronized (this) {
            while (q.size() == this.capacity) {
                wait();
            }
            q.add(item);
            notifyAll();
        }

    }

    public String take() throws InterruptedException {
        synchronized (this) {
            while (q.isEmpty()) {
                wait();
            }
            String item = q.remove(0);
            notifyAll();
            return item;
        }
    }
}



