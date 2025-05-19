package com.example.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class AppConfig {
    
    
    @Bean
    @Scope("singleton")
    public Sample SingletonService() {
        return new Sample("I am Singleton");
    }

    // @Bean
    // @Scope("prototype")
    // public Sample PrototypeService() {
    //     return new Sample("I am prototype");
    // }

    // @Bean
    // @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    // public Sample SessionService() {
    //     return new Sample("I am Session");
    // }


    // @Bean
    // @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    // public Sample RequestService() {
    //     return new Sample("I am Request");
    // }


    // @Bean
    // @Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    // public Sample ApplicationService() {
    //     return new Sample("I am Application");
    // }



}
