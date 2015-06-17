package com.produceconsumer;

/**
 * User: May
 * Date: 2015-06-17
 * Time: 10:35
 */
public class TestTwo implements Runnable {
    volatile int apples = 0;
    MyProducer producer = new MyProducer(this);
    MyConsumer consumer = new MyConsumer(this);

    @Override
    public void run() {
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    public static void main(String[] args) {
        new Thread(new TestTwo()).start();
    }
}

class MyProducer implements Runnable {
    TestTwo test;

    MyProducer(TestTwo test) {
        this.test = test;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (this) {
                if (test.apples >= 5) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (test.consumer) {
                System.out.println("Get an apple from box : size = " + ++test.apples);
                try {
                    Thread.sleep((long)Math.random() % 1000);
                    test.consumer.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class MyConsumer implements Runnable {

    TestTwo test;

    MyConsumer(TestTwo test) {
        this.test  = test;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (this) {
                if (test.apples <= 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (test.producer) {
                System.out.println("Get an apple from box : size = " + --test.apples);
                try {
                    Thread.sleep((long)Math.random() % 1000);
                    test.producer.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
