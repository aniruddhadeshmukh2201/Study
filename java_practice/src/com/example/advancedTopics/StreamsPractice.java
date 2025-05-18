package com.example.advancedTopics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsPractice {
    
    
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");

        names.stream().forEach((item)-> System.out.println(item));



        List<String> cities = Arrays.asList("mumbai", "pune", "hingoli");
        cities.stream().forEach((item) -> System.out.println(item));

        Stream<String> st = Stream.of("x", "y", "z");
        st.forEach(System.out::println);

    }
}
