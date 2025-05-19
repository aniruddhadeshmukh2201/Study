package com.example.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class AppConfig {
    
    
    @Bean("SingletonService")
    @Scope("singleton")
    public Sample SingletonService() {
        return new Sample("I am Singleton");
    }

    @Bean("PrototypeService")
    @Scope("prototype")
    public Sample PrototypeService() {
        return new Sample("I am prototype");
    }

    @Bean("SessionService")
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Sample SessionService() {
        return new Sample("I am Session");
    }


    @Bean("RequestService")
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Sample RequestService() {
        return new Sample("I am Request");
    }


    @Bean("ApplicationService")
    @Scope(value = "application", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Sample ApplicationService() {
        return new Sample("I am Application");
    }



}
