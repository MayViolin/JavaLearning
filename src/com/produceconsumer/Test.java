package com.produceconsumer;

/**
 * Created by MayViolin on 2015/6/16.
 */
public class Test extends Thread{
    private int apples = 0;
    Producer myProducer = new Producer(this);
    Consumer myConsumer = new Consumer(this);

    public int countApples() {
        return apples;
    }

    public void putOneApple() {
        ++apples;
    }

    public void getAwayOneApple() {
        --apples;
    }

    @Override
    public void run() {
        myProducer.start();
        myConsumer.start();
    }

    public static void main(String[] args) {
        Thread thread = new Test();
        thread.start();
    }
}

class Producer extends Thread{
    private Test test;

    Producer(Test test) {
        this.test = test;
    }

    public void run() {

        while (!Thread.interrupted()) {
            synchronized (this) {
                if (test.countApples() >= 5) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (test.myConsumer) {
                test.putOneApple();
                System.out.println("Put an apple in Box, size = " + test.countApples());
//                test.myConsumer.notifyAll();
                try {
                    sleep(1000);
                    test.myConsumer.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

class Consumer extends Thread{

    private Test test;

    Consumer(Test test) {
        this.test = test;
    }

    public void run() {
        while (!Thread.interrupted()) {
            synchronized (this) {
                if (test.countApples() <= 0) {
                    try {
                        wait();
                        notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (test.myProducer) {
                test.getAwayOneApple();
                System.out.println("Get an apple from Box, size = " + test.countApples());
                try {
                    sleep(1000);
                    test.myProducer.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
