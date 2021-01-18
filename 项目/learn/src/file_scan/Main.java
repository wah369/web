package file_scan;


import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-18
 * Time: 22:09
 */
public class Main {
    public static void main(String[] args) {
        File root = new File("D:\\QQ\\1808154326\\FileRecv\\2021-1-18-春招冲刺班-项目1");


        /*String[] list = root.list();
        System.out.println(Arrays.toString(list));*/

        /*File[] files = root.listFiles();
        System.out.println(Arrays.toString(files));*/

        /*File[] files = root.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                System.out.println(name);
                if (name.endsWith(".png")) {
                    return true;
                }
                return false;
            }
        });
        System.out.println(Arrays.toString(files));*/
        //traversalDepth(root);
        traversalBroadcast(root);
    }
    //广度优先 -层序遍历-队列
    private static void traversalBroadcast(File root) {
        //所有文件都放入队列
        Queue<File> queue = new LinkedList<>();
        queue.offer(root);//先把root放进去

        while (!queue.isEmpty()) {
            File file = queue.poll();
            boolean directory = file.isDirectory();
            if (directory) {
                System.out.println("文件夹:" + file);
            }else {
                System.out.println("普通文件:" + file);
            }

            File[] files = file.listFiles();
            if (files == null) {
                continue;
            }
            if (files.length == 0) {
                continue;
            }
            for (File child : files) {
                queue.offer(child);
            }
        }
    }

    //深度优先 -栈 -递归
    private static void traversalDepth(File root) {
        System.out.println("文件夹:" + root);
        File[] files = root.listFiles();

        //判断是不是叶子节点
        if (files == null) {
            return;
        }
        if (files.length == 0) {
            return;
        }
        //判断结束

        //针对每个孩子，进行递归处理
        //只处理文件夹孩子即可
        for (File file : files) {
            if (!file.isDirectory()) {  //如果file不是目录
                System.out.println("普通文件：" + file);
                continue;
            }

            traversalDepth(file);
        }
    }



}
