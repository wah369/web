package com.wah.dao;

import com.wah.util.DBUtil;
import com.wah.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-25
 * Time: 22:43
 */
public class DeleteDAO {
    public void delete(List<Integer> idList)  {
        try {
            Connection c = DBUtil.getConnection();      // 由于实现成，一个线程只有一个 Connection 对象了，所以不需要关闭连接
            // JDK8 中支持的 stream 用法
            List<String> placeholderList = idList.stream()
                    .map(id -> "?")
                    .collect(Collectors.toList());
//        List<String> placeholderList = new ArrayList<>();
//        for (Integer id : idList) {
//            placeholderList.add("?");
//        }

            String placeHolder = String.join(", ", placeholderList);

            String sql = String.format("DELETE FROM file_meta WHERE id IN (%s)", placeHolder);

            try (PreparedStatement s = c.prepareStatement(sql)) {
                for (int i = 0; i < idList.size(); i++) {
                    int id = idList.get(i);
                    s.setInt(i + 1, id);
                }
                int i = s.executeUpdate();
                LogUtil.log("执行 SQL: %s, %s,收到影响的行数是%d", sql, idList, i);
                s.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws SQLException {
        List<Integer> idList = Arrays.asList(1, 2, 3);

        DeleteDAO dao = new DeleteDAO();
        dao.delete(idList);
    }
}

