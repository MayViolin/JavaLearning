package com.test.TransFormer;

/**
 * User: May
 * Date: 2015-05-05
 * Time: 13:08
 */
public class EnergySystem {
    private final double[] energyBox;

    private final Object lock = new Object();

    public EnergySystem(int n, double initialEnergy) {
        energyBox = new double[n];
        for (int i = 0;i < n; i++) {
            energyBox[i] = initialEnergy;
        }
    }


    public void transfor(int from, int to, double energyAmount) {
//        if (energyBox[from] < energyAmount) {
//            return;
//        }
        synchronized (lock) {
            while (energyBox[from] < energyAmount) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            energyBox[from] -= energyAmount;
            System.out.printf("%d转移%10.2f单位能量到%d%n", from, energyAmount, to);
            energyBox[to] += energyAmount;
            System.out.printf("能量总和为:%10.2f%n", getTotalEnergy());
            lock.notifyAll();
        }


    }

    public double getTotalEnergy() {
        double sum = 0;
        for (double amount : energyBox) {
            sum += amount;
        }
        return sum;
    }

    public int getBoxSize() {
        return energyBox.length;
    }
}
