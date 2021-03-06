import java.math.BigDecimal;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-11-20
 * Time: 10:31
 */
public class test {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类，即将该类加载到JVM方法区，并执行该类的静态方法块、静态属性。
            Class.forName("com.mysql.jdbc.Driver");

            // 第一步：创建数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chong?user=root&password=123456&useUnicode=true&useSSL=false&characterEncoding=UTF-8");
            System.out.println(connection);

            // 第二步：创建了操作命令对象
            statement = connection.createStatement();

            // 第三步：执行sql
            resultSet = statement.executeQuery("select id,name,role,salary from emp");

            // 第四步：处理结果集ResultSet（类似List<Map<String, 字段类型>>）
            while (resultSet.next()){//遍历每一行数据
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");
                BigDecimal salary = resultSet.getBigDecimal("salary");
                System.out.printf("id=%s, name=%s, role=%s, salary=%s%n",
                        id, name, role, salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
