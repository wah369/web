/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-23
 * Time: 11:38
 */
public class 观察线程安全问题 {
    //为什么count越大，正确率越低
    private static final int COUNT = 100;
    private static int a = 0;
    private static class Adder extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < COUNT; i++) {
                a++;
            }
        }
    }
    private static class Suber extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < COUNT; i++) {
                a--;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread adder = new Adder();
        Thread suber = new Suber();

        adder.start();suber.start();
        adder.join();suber.join();

        System.out.println(a);
    }
}
