package com.example.Interview_practice;
import java.util.concurrent.atomic.AtomicInteger;



public class SafeCounter {
    private final AtomicInteger count = new AtomicInteger(0);    

    public synchronized int Increment() {
        return count.incrementAndGet();
    }

    public int get() {
        return count.get();
    }
}
