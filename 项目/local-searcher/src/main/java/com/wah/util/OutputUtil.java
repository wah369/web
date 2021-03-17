package com.wah.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-25
 * Time: 22:46
 */
public class OutputUtil {
    public static String formatLength(Long length) {
        if (length < 1024) {
            return length + "字节";
        }
        if (length < 1024 * 1024) {
            return (length / 1024) + "KB";
        }
        if (length < 1024 * 1024 * 1024) {
            return (length / 1024 / 1024) + "MB";
        }

        return (length / 1024 / 1024 / 1024) + "GB";

    }

    public static String formatTimestamp(Long lastModifiedTimestamp) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(lastModifiedTimestamp);
        return format.format(date);

    }
}
