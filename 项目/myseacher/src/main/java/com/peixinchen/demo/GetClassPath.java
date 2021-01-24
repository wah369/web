package com.peixinchen.demo;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class GetClassPath {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String classesPath = GetClassPath.class.getProtectionDomain()
                .getCodeSource().getLocation().getFile();
        System.out.println(classesPath);
        String decode = URLDecoder.decode(classesPath, "UTF-8");
        System.out.println(decode);
        File classesDir = new File(decode);
        System.out.println(classesDir.getAbsoluteFile());
    }
}
