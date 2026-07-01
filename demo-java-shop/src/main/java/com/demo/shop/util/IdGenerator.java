package com.demo.shop.util;

import java.util.concurrent.atomic.AtomicLong;

/** Generates monotonic, prefixed identifiers for demo entities. */
public final class IdGenerator {

    private static final AtomicLong COUNTER = new AtomicLong(1000);

    private IdGenerator() {}

    public static String next(String prefix) {
        return prefix + "-" + COUNTER.incrementAndGet();
    }
}
