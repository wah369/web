package com.wah.util;

import com.wah.model.FileMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-27
 * Time: 19:33
 */
public class ListUtil {
    //写一个泛型方法
    public static <E> List<E> differenceSet(List<E> list1, List<E> list2) {
        List<E> resultList = new ArrayList<>();

        for (E item : list1) {
            if (!list2.contains(item)) {   //E类型必须支持equals方法
                resultList.add(item);
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        List<FileMeta> list1 = Arrays.asList(
                new FileMeta(new File("E:\\xiangmu\\板书1.png")),
                new FileMeta(new File("E:\\xiangmu\\板书3.png"))

        );

        List<FileMeta> list2 = Arrays.asList(
                new FileMeta(new File("E:\\xiangmu\\板书3.png")),
                new FileMeta(new File("E:\\xiangmu\\板书4.png"))

        );

        for (FileMeta fm : differenceSet(list1, list2)) {    // 1
            System.out.println(fm);
        }
        System.out.println("=============================");
        for (FileMeta fm : differenceSet(list2, list1)) {    // 4
            System.out.println(fm);
        }
    }
}
