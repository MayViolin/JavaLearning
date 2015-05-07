package com.coreandthread;

/**
 * Created by May on 2015/5/5.
 */
public class ThreadTest extends Thread{

    private boolean stop = false;

    public void run() {
        int i = 0;
        while (!stop) {
            System.out.println(Thread.currentThread().getName() + "is running " + i + " times");
            ++i;
            if (i == Integer.MAX_VALUE) {
                stop = true;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new ThreadTest();
        t1.setName("ThreadOne");
        Thread t2 = new ThreadTest();
        t2.setName("ThreadTwo");
        Thread t3 = new ThreadTest();
        t3.setName("ThreadThree");
        Thread t4 = new ThreadTest();
        t4.setName("ThreadFour");
        Thread t5 = new ThreadTest();
        t5.setName("ThreadFive");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

}
