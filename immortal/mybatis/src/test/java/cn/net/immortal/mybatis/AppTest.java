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
        //构建sqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //这个流不用自己关闭
        InputStream resourceAsStream = Resources.getResourceAsStream(configXml);
        //DefaultSqlSessionFactory
        this.sqlSessionFactory = builder.build(resourceAsStream);
    }


    @Test
    public void mybatisSourceTest(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        //根据type从mapperRegistry由MapperProxyFactory创建一个MapperProxy
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //动态代理 InvocationHandler.invoke
        List<User> userList = userMapper.getUserList();
        List<User> userList2 = userMapper.getUserList();
        assert userList == userList;
        userList.forEach(System.out::print);
    }
}
