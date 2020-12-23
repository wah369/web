/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-23
 * Time: 10:19
 */
public class 通知线程停止 {

    private static class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("hello world");
                boolean 有人赐白绫 = Thread.interrupted();
                if (有人赐白绫) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.start();

        Thread.sleep(3000);

        t.interrupt();
    }
}
