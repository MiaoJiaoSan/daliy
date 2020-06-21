package cn.net.immortal.mybatis.jdbc;

import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.StatementImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * jdbc连接数据库的6步
 */
public class JdbcOperation {

    private final static String username = "user";
    private final static String password = "user";
    private final static String url="jdbc:mysql://www.immortal.net.cn:3306/user?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private final static String driverClass = "com.mysql.cj.jdbc.Driver";
    private final String sql = "select id,account_id,name,phone,email from user where id = ?";

    @Before
    public void registryDriver() throws ClassNotFoundException {
        //1、加载驱动
        Class.forName(driverClass);
    }


    @Test
    public void operation() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //2、创建连接
            connection = DriverManager.getConnection(url, username, password);
            //3、创建Statement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, 1L);
            //4、执行sql返回结果集
            resultSet = preparedStatement.executeQuery();
            //5、遍历结果集
            while (resultSet.next()) {
                resultSet.getLong("id");
                System.out.println(resultSet.getString("name"));
            }
        }finally {
            //6、关闭连接
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
    }


    /**
     * 多线程事物是然并卵的
     * @throws SQLException
     */
    @Test
    public void operationAsync() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            //2、创建连接
            connection = DriverManager.getConnection(url, username, password);

            //3、创建Statement
            preparedStatement1 = connection.prepareStatement(sql);
            preparedStatement2 = connection.prepareStatement(sql);
            Field field = StatementImpl.class.getDeclaredField("connection");
            field.setAccessible(true);
            JdbcConnection o1 = (JdbcConnection) field.get(preparedStatement1);
            JdbcConnection o2 = (JdbcConnection) field.get(preparedStatement2);
            System.out.println(o1.hashCode() == o2.hashCode());
            System.out.println(o1.getConnectionMutex().hashCode() == o1.getConnectionMutex().hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6、关闭连接
            preparedStatement2.close();
            preparedStatement1.close();
            connection.close();
        }
    }
}
