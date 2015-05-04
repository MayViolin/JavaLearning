/**
 * User: May
 * Date: 2015-05-04
 * Time: 17:54
 */
public class Actors extends Thread{

    public void run() {
        System.out.println(getName() + " is a actor!");

        int count = 0;
        boolean stop = false;
        while (!stop) {
            System.out.println(getName() + " is running:" + (++count));
            if (count % 10 == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (count == 100) {
                stop = true;
            }
        }

        System.out.println(getName() + " is over!");
    }

    public static void main(String[] args) {
        Thread actor = new Actors();
        actor.setName("Mr.Thread");
        actor.start();

        Thread actress = new Thread(new Actress(),"Ms.Runnable");
        actress.start();
    }
}

class Actress implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is a actor!");

        int count = 0;
        boolean stop = false;
        while (!stop) {
            System.out.println(Thread.currentThread().getName() + " is running:" + (++count));
            if (count % 10 == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (count == 100) {
                stop = true;
            }
        }

        System.out.println(Thread.currentThread().getName() + " is over!");
    }
}
