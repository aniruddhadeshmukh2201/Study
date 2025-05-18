package com.example.core_oop;


class Asd {
    public void a() {
        System.out.println("from asd class, a method : ");
    }
}

class Bsd extends Asd {
    public void a() {
        System.out.println("from bsd class , a method : ");
    }
}

public class OverridingPractice {
     public static void main(String[] args) {
        Asd x = new Asd();
        x.a();

        Asd y = new Bsd();
        y.a();


        Bsd z = new Bsd();
        z.a();
    }   
}
