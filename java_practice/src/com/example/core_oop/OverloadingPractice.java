package com.example.core_oop;




class Q {
    public void a(int a) {
        System.out.println("from first");
    }

    public void a(int a, int b) {
        System.out.println("from second");
    }
}



public class OverloadingPractice {
    public static void main(String[] args) {
        Q b = new Q();
        
        b.a(1);
        b.a(1,  2);
    }   

}
