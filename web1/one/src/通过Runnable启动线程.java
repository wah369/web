/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-21
 * Time: 11:43
 */
public class 通过Runnable启动线程 {
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            while (true) {
                System.out.println("我是子线程");
            }
        }
    }
    //Thread  线程
    //Runnable 让这个线程要做的工作
    public static void main(String[] args) {
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        while (true) {
            System.out.println("我是父线程");
        }
    }
}
