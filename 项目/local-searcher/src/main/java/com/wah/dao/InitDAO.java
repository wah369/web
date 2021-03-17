package com.wah.dao;

import com.wah.util.DBUtil;
import com.wah.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-25
 * Time: 22:43
 */
public class InitDAO {
    //找到init.sql文件，并读取其内容
    //得到一组SQL语句String[]
    // init.sql 是一组以 ';' 作为分割的多个 SQL 语句
    private String[] getSQLs() {
        try (InputStream is = InitDAO.class.getClassLoader().getResourceAsStream("init.sql")) {
            StringBuilder sb = new StringBuilder();
            Scanner scanner = new Scanner(is, "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sb.append(line);
            }

            return sb.toString().split(";");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //执行
    public void init() {
        try {
            Connection c = DBUtil.getConnection();

            String[] sqls = getSQLs();
            for (String sql : sqls) {
                LogUtil.log("执行 SQL: " + sql);
                try (PreparedStatement s = c.prepareStatement(sql)) {
                    if (sql.toUpperCase().startsWith("SELECT ")) {  //如果SQL语句以SELECT开头
                        try (ResultSet resultSet = s.executeQuery()) {
                            ResultSetMetaData metaData = resultSet.getMetaData();
                            int columnCount = metaData.getColumnCount();  //从metaData信息中得到有多少列
                            int rowCount = 0;
                            while (resultSet.next()) {
                                StringBuilder message = new StringBuilder("|");
                                for (int i = 1; i <= columnCount; i++) {    //针对每一行遍历
                                    String value = resultSet.getString(i);
                                    message.append(value).append(" | ");
                                }
                                LogUtil.log(message.toString());
                                rowCount++;
                            }
                            LogUtil.log("一共查询出 %d 行", rowCount);
                        }
                    } else {
                        int i = s.executeUpdate();
                        LogUtil.log("收到影响的一共有 %d 行", i);

                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        InitDAO initDAO = new InitDAO();
        initDAO.init();
    }
}
