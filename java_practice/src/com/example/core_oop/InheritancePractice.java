package com.example.core_oop;

class Parent {
    public void a() {
        System.out.println("from a");
    }
}

class Child extends Parent {
    public void b() {
        System.out.println("from b");
    }
}

public class InheritancePractice {
    public static void main(String[] args) {
        Parent x = new Child();

        x.a();

        Child y = (Child) x;
        y.a();
        y.b();
    }
}
