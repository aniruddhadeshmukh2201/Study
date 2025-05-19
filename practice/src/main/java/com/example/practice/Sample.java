package com.example.practice;

public class Sample {

    private int count = 0;
    public Sample(String s) {
        System.out.println("from contructor : " + s);
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }
}
