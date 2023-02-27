package com.bsuir.labs.demo.counter;

public class Counter {

    private static int COUNTER = 0;

    synchronized public static int getCounter() {
        return COUNTER;
    }

    public static int increment() {
        return ++COUNTER;
    }


}
