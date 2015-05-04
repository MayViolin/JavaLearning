/**
 * User: May
 * Date: 2015-05-04
 * Time: 16:25
 */
public class KeyPerson extends Thread {

    public void run() {
        System.out.println(getName() + "is a key person!");

        int count = 0;

        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println(getName() + " is on the stage.." + (++count));
            if (count % 10 == 0) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (count == 100) {
                keepRunning = false;
            }
        }

        System.out.println(getName() + "is leaving the stage!");
    }

    public static void main(String[] args) {
        Thread keyPerson = new KeyPerson();
        //keyPerson.start();
        keyPerson.setName("Mr.Thread");
        keyPerson.run();
    }
}
