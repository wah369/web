import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-23
 * Time: 9:21
 */
public class ThreadSortDemo {
    private static void bubbleSort(long[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    long t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }

    private static final Random random = new Random(20201223);

    private static long[] createRandomArray(int n) {
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(2 * n);
        }
        return array;
    }

    private static class SortThread extends Thread {
        private final long[] array;

        SortThread(long[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            bubbleSort(array);
        }
    }

    private static void 多线程排序() throws InterruptedException {
        long[] a1 = createRandomArray(4_0000);
        long[] a2 = createRandomArray(4_0000);
        long[] a3 = createRandomArray(4_0000);
        long[] a4 = createRandomArray(4_0000);
        long[] a5 = createRandomArray(4_0000);

        System.out.println("开始排序");

        long s = System.currentTimeMillis();

        Thread t1 = new SortThread(a1);
        Thread t2 = new SortThread(a2);
        Thread t3 = new SortThread(a3);
        Thread t4 = new SortThread(a4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        bubbleSort(a5);

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        long e = System.currentTimeMillis();

        double sec = (e - s) / 1000.0;
        System.out.printf("多线程：一共耗时 %f 秒\n", sec);
    }

    private static void 单线程排序() {
        long[] a1 = createRandomArray(4_0000);
        long[] a2 = createRandomArray(4_0000);
        long[] a3 = createRandomArray(4_0000);
        long[] a4 = createRandomArray(4_0000);
        long[] a5 = createRandomArray(4_0000);

        System.out.println("开始排序");
        long s = System.currentTimeMillis();
        bubbleSort(a1);
        bubbleSort(a2);
        bubbleSort(a3);
        bubbleSort(a4);
        bubbleSort(a5);
        long e = System.currentTimeMillis();

        double sec = (e - s) / 1000.0;
        System.out.printf("单线程：一共耗时 %f 秒\n", sec);
    }

    public static void main(String[] args) throws InterruptedException {
        多线程排序();
    }
}
