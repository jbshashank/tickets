package com.ayushya.spring.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class LocalCacheEvict {
    @CacheEvict(cacheNames = "student",allEntries = true)
    public void evictAllUsersCache() {
    	System.out.println("evicting cache");
    }
}