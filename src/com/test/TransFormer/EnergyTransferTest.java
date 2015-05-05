package com.test.TransFormer;

/**
 * User: May
 * Date: 2015-05-05
 * Time: 14:04
 */
public class EnergyTransferTest {

    public static void main(String[] args) {
        int n = 100;
        int initialEnergy = 1000;
        for (int i = 0; i < n; i++) {
            EnergyTransferTask task = new EnergyTransferTask(new EnergySystem(n,initialEnergy),i,initialEnergy);
            Thread test = new Thread(task,"Transfer_Thread_" + i);
            test.start();
        }
    }
}
