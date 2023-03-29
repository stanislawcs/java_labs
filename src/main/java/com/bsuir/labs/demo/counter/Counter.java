package com.bsuir.labs.demo.counter;

public class Counter {

    private static int COUNTER = 0;

    public static int getCounter() {
        return COUNTER;
    }

    public synchronized static void increment() {
        ++COUNTER;
    }


}
