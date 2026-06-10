package com.example.practice;

public class InsurerRequestDTO {
    String name;
    Integer sal;

    public InsurerRequestDTO(String name, Integer sal) {
        this.name = name;
        this.sal = sal;
    }


    public String getName() {
        return this.name;
    }

    public Integer getSal(){
        return this.sal;
    }
}
