package cn.net.immortal.mybatis.jdbc;

import org.junit.Before;
import org.junit.Test;

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
}
