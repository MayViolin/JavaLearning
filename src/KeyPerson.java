/**
 * User: May
 * Date: 2015-05-04
 * Time: 16:25
 */
public class KeyPerson extends Thread {

    public void run() {
        System.out.println(Thread.currentThread().getName() + " begin to fight!");

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " is fighting now!");
        }

        System.out.println(Thread.currentThread().getName() + " stop fighting!");
    }

    public static void main(String[] args) {
        Thread keyPerson = new KeyPerson();
        //keyPerson.start();
        keyPerson.setName("Mr.Thread");
        keyPerson.run();
    }
}
