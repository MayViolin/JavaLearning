/**
 * User: May
 * Date: 2015-05-04
 * Time: 16:26
 */
public class HistoryStage extends Thread {

    public void run() {
        System.out.println("The battle is coming!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Army suiArmy = new Army();
        Army revoltArmy = new Army();

        Thread armyOfSui = new Thread(suiArmy,"Sui_Army");
        Thread armyOfRevolt = new Thread(revoltArmy,"Revolt_Army");

        //启动线程
        armyOfSui.start();
        armyOfRevolt.start();

        //舞台线程休眠
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Mr.Cheng is coming!");

        Thread cheng = new KeyPerson();
        cheng.setName("Cheng Yaojin");
        System.out.println("Cheng is dreaming to stop the fight!");

        //停止线程的方法:stop()不可取，应用退出标志
        suiArmy.keepFighting = false;
        revoltArmy.keepFighting = false;
//        armyOfSui.stop();
//        armyOfRevolt.stop();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cheng.start();

        try {
            cheng.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The battle is over!");
    }


    public static void main(String[] args) {
        new HistoryStage().start();
    }

}
