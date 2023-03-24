package com.bsuir.labs.demo.counter;

import org.springframework.stereotype.Component;

@Component
public class CounterThread extends Thread {

    public CounterThread() {
        super();
    }

    @Override
    public void run() {
            Counter.increment();
    }


}
