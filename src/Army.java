/**
 * User: May
 * Date: 2015-05-04
 * Time: 16:24
 */
public class Army implements Runnable {

    //保证该变量可以被线程正确的读取
    //关于可见性可详见Java内存模型中的阐述，happens-before模型
    volatile boolean keepFighting = true;

    @Override
    public void run() {
        while (keepFighting) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "fighting " + i + " with others!");
                Thread.yield();// 让出CPU时间
            }
        }
    }
}
