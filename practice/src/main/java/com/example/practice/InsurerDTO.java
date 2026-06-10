package com.example.practice;


public class InsurerDTO {
    private String name;
    private Integer sal;
    private String insurerName;

    public InsurerDTO(String name, Integer sal, String insureName) {
        this.name = name;
        this.insurerName = insureName;
        this.sal = sal;
    }
}
