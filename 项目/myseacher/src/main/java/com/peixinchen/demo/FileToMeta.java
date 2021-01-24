package com.peixinchen.demo;

import java.io.File;
import java.util.Date;

public class FileToMeta {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\bit\\Desktop\\mysearcher\\mysearcher.db");
        System.out.println(file.getName());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.isDirectory());
        System.out.println(file.length());
        System.out.println(file.lastModified());

        Date date = new Date(file.lastModified());
        System.out.println(date);
    }
}
