package com.example.practice;




public class Insurer {
    private String name;
    private Integer bracketStart;
    private Integer bracketEnd;

    public Insurer(String name, Integer bracketStart, Integer bracketEnd) {
        this.name = name;
        this.bracketStart = bracketStart;
        this.bracketEnd = bracketEnd;

        System.out.println("insurer created");
    }

    public String getName() {
        return this.name;
    }

    public Integer getBracketStart() {
        return this.bracketStart;
    }

    public Integer getBracketEnd() {
        return this.bracketEnd;
    }



}