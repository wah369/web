package com.wah.task;

import com.wah.model.FileMeta;
import com.wah.service.FileService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-25
 * Time: 22:45
 */
public class FileScanner {
    private final FileService fileService = new FileService();

    public void scan(File root) {
        scanDir(root);
    }

    private void scanDir(File root) {
        if (!root.isDirectory()) {
            return;
        }

        File[] children = root.listFiles();
        if (children == null) {
            return;
        }
        //扫描结果
        List<FileMeta> scanResultList = new ArrayList<>();
        for (File child : children) {
            scanDir(child);
            if (child.isFile()) {
                scanResultList.add(new FileMeta(child));
            }
        }
        //数据库查询结果集,根据路径查
        fileService.service(root.getAbsolutePath(),scanResultList);
    }
}
