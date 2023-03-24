package com.bsuir.labs.demo.counter;

public class Counter {

    private static int COUNTER = 0;

    synchronized public static int getCounter() {
        return COUNTER;
    }

    synchronized public static void increment() {
        ++COUNTER;
    }


}
