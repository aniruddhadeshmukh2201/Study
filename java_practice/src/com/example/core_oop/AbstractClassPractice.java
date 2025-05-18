package com.example.core_oop;

abstract class Parentt {
    public abstract void a();

    public void b() {
        System.out.println("from b");
    }
}

class Childd extends Parentt {
    public void a() {
        System.out.println("from concrete a");
    }
}

public class AbstractClassPractice {
    public static void main(String[] args) {
        Parentt x = new Childd();

        x.a();
        x.b();
    }
}
