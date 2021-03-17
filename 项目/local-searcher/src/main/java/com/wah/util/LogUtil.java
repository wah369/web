package com.wah.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-26
 * Time: 17:02
 */
public class LogUtil {
    //类型...   可变参数列表
    //Object... 代表任意类型任意长度都可以传入
    public static void log(String format, Object... args) {
        String message = String.format(format,args);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String now = dateFormat.format(date);
        System.out.printf("%s: %s\n", now, message);
    }

    public static void main(String[] args) {
        log("你好");
        log("今天有%d 个用血来上课",18);



    }
}
