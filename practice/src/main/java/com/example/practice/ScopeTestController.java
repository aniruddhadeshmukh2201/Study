package com.example.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScopeTestController {

    @Autowired
    @Qualifier("RequestService")
    Sample r;

    @Autowired
    @Qualifier("SessionService")
    Sample s;

    @Autowired
    @Qualifier("ApplicationService")
    Sample a;

    @GetMapping("/test")
    public String test() {
        System.out.println("request bean Hash : " + r.hashCode() + " : " + r.getCount());
        System.out.println("session bean Hash : " + s.hashCode() + " : " + s.getCount());
        System.out.println("application bean Hash : " + a.hashCode() + " : " + a.getCount());
        return "Scope beans invoked. check console";
    }
}
