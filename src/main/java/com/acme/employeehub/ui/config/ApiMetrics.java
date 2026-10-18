package com.acme.employeehub.ui.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ApiMetrics {
    private static final ConcurrentHashMap<String, AtomicLong> COUNTS = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AtomicLong> TOTAL_TIMES = new ConcurrentHashMap<>();

    public static void record(String endpoint, long durationMs) {
        COUNTS.computeIfAbsent(endpoint, key -> new AtomicLong()).incrementAndGet();
        TOTAL_TIMES.computeIfAbsent(endpoint, key -> new AtomicLong()).addAndGet(durationMs);
    }

    public static long getCount(String endpoint) {
        return COUNTS.getOrDefault(endpoint, new AtomicLong()).get();
    }

    public static long getAverageTimeMs(String endpoint) {
        long count = getCount(endpoint);
        long total = TOTAL_TIMES.getOrDefault(endpoint, new AtomicLong()).get();
        return count == 0 ? 0 : total / count;
    }
}
