package com.example.Interview_practice;

import java.util.Date;

public final class Immutable {

    private final String name;
    private final Integer age;
    private final Date date;


    public Immutable(String name, Integer age, Date date) {
        this.name = name;
        this.age = age;
        this.date = new Date(date.getTime());
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }

    public Date getDate() {
        return new Date(this.date.getTime()); 
    }

}


