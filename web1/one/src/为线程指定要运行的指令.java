/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-21
 * Time: 11:24
 */
public class 为线程指定要运行的指令 {
    static class MyTread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("我是子线程");
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new MyTread();
        thread.start();   //只是修改
        while (true) {
            System.out.println("我是父线程");
        }
    }
}
