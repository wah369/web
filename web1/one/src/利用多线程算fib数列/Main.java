package 利用多线程算fib数列;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-21
 * Time: 11:57
 */
public class Main {
    private static long fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    private static class MyThread extends Thread {
        private int n;

        MyThread(int n) {
            this.n = n;
        }
        @Override
        public void run() {
            System.out.println(fib(n));
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            int n  = scan.nextInt();
            Thread thread = new MyThread(n);
            thread.start();
        }
    }



}
