package com.wah.dao;

import com.wah.model.FileMeta;
import com.wah.util.DBUtil;
import com.wah.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-25
 * Time: 22:43
 */

/*
SaveDAO:
最终用户/场景： 保存扫描出的文件列表
 */
public class SaveDAO {
    public void save(List<FileMeta> fileList) {
        try {
            String sql = "INSERT INTO file_meta " +
                    "(name, path, is_directory, pinyin, pinyin_first, size, last_modified) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection c = DBUtil.getConnection(); //拿到链接

            try (PreparedStatement s = c.prepareStatement(sql)) {
                // 一个一个文件插入 OR 批量插入
                for (FileMeta file : fileList) {
                    s.setString(1, file.getName());
                    s.setString(2, file.getPath());
                    s.setBoolean(3, file.isDirectory());
                    s.setString(4, file.getPinyin());
                    s.setString(5, file.getPinyinFirst());
                    s.setLong(6, file.getLength());
                    s.setLong(7, file.getLastModifiedTimestamp());

                    int i = s.executeUpdate();
                    LogUtil.log("执行 SQL: %s, %s, 收到影响的行数是: %d", sql, file, i);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        List<FileMeta> fileList = Arrays.asList(
                new FileMeta(new File("E:\\xiangmu\\板书1.png")),
                new FileMeta(new File("E:\\xiangmu\\板书3.png")),
                new FileMeta(new File("E:\\xiangmu\\板书4.png"))

        );
        SaveDAO saveDAO = new SaveDAO();
        saveDAO.save(fileList);
    }
}
