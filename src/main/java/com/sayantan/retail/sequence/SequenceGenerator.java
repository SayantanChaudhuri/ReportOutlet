package com.sayantan.retail.sequence;

public final class SequenceGenerator {

    private static long value = 1000;

    private SequenceGenerator() {

    }

    public static long getNext() {
        return value++;
    }
}
