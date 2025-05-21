package com.example.Interview_practice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DataCache {
    private final Map<String, Object> cache = new ConcurrentHashMap<>();



    DataCache() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
            this::refreshCache, 0, 5, TimeUnit.MINUTES
        );
    }


    public  Object get(String key) {
        return this.cache.get(key);
    }


    public void refreshCache() {
        // put the fresh data from the db to the cache
    }

}
