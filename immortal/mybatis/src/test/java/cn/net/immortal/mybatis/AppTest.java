package cn.net.immortal.mybatis;

import cn.net.immortal.mybatis.entity.User;
import cn.net.immortal.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AppTest {

    private String configXml = "mybatis-config.xml";

    private SqlSessionFactory sqlSessionFactory;


    @Before
    public void addConfig() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = Resources.getResourceAsStream(configXml);
        this.sqlSessionFactory = builder.build(resourceAsStream);
        resourceAsStream.close();
    }


    @Test
    public void mybatisSourceTest(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();
        userList.forEach(System.out::print);
    }
}
