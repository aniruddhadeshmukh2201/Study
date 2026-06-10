package com.example.practice;

public class Singleton {
    
    private static Singleton instance;
    private 

    private Singleton() {

    }


    public static Singleton getInstance(){
        if(instance != null) {
            return instance;
        }
        instance = new Singleton();
        return instance;
    }
}


20 = tata
40 = icici
40+ = aig



name, sal => name, sal, insurer name