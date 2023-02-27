package com.bsuir.labs.demo.cache;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class Cache<K, V> {
    Map<K, V> cache = Collections.synchronizedMap(new HashMap<K, V>());

    public boolean contain(K request) {
        return cache.containsKey(request);
    }

    public void push(K request, V response) {
        if (!cache.containsKey(request)) {
            cache.put(request, response);
        }
    }

    public V get(K request) {
        return cache.get(request);
    }
}
