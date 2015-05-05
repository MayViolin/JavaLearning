package com.test.TransFormer;

/**
 * User: May
 * Date: 2015-05-05
 * Time: 13:19
 */
public class EnergyTransferTask implements Runnable {

    private EnergySystem energySystem;

    private int fromBox;

    private static int DELAY = 10;

    private double maxAmount;

    public EnergyTransferTask(EnergySystem energySystem, int fromBox, double maxAmount) {
        this.energySystem = energySystem;
        this.fromBox = fromBox;
        this.maxAmount = maxAmount;
    }


    @Override
    public void run() {

        while (true) {
            int toBox = (int)(energySystem.getBoxSize() * Math.random());
            double amount = maxAmount * Math.random();
            energySystem.transfor(fromBox, toBox, amount);
            try {
                Thread.sleep((int)(DELAY * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
