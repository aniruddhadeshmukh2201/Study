package com.example.core_oop;


interface A {
    public void b(int a, int b);
}


class Aconcrete implements A {
    public void b(int a, int b){
        System.out.println(a + " : " + b);
    }
}

public class InterfacePractice {
    public static void main(String[] args) {
        A a = new Aconcrete();
        a.b(2, 3);
    }
}
