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


/**
 * 大致流程SqlSessionFactoryBuilder 创建 SqlSessionFactory 并设置Configuration到SqlSessionFactory
 * Configuration mapperRegistry属性 存放key = MapperInterface， value = MapperProxyFactory<MapperInterface>
 * getMapper时MapperProxyFactory创建MapperProxy返回。
 *
 *
 */
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
        //sqlSession的主要工作是执行sql
        //大致看作statement + result + 其他一些能力（缓存/日志）的封装
        //sqlSession中包含Executor CacheExecutor是二级缓存,BaseExecutor中存在一级缓存
        //openSession中创建Executor和sqlSession
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        //不使用getMapper的方式
        //这个地方貌似解释了为什么mybatis不适用注解只能有一个参数
        List<User> userList = sqlSession.selectList("cn.net.immortal.mybatis.mapper.UserMapper.getUserList",1L);
        //根据type从mapperRegistry由MapperProxyFactory创建一个MapperProxy
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //动态代理 InvocationHandler.invoke
//        List<User> userList = userMapper.getUserList();
        assert userList == userList;
        userList.forEach(System.out::print);
    }
}
