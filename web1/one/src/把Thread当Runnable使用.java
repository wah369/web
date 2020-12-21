/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-21
 * Time: 11:50
 */
public class 把Thread当Runnable使用 {
    static class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("我是子线程");
            }
        }
    }

    public static void main(String[] args) {
        Thread runnable = new MyThread();
        Thread thread = new Thread(runnable);
        thread.start();

        while (true) {
            System.out.println("我是主线程");
        }
    }
}
