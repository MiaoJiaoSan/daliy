package cn.net.immortal.spring;

import static org.junit.Assert.*;

import cn.net.immortal.spring.beans.MyAnnotationBean;
import cn.net.immortal.spring.beans.MyXmlBean;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * 注解配置方式
     */
    @Test
    public void annotationConfigApplicationContextTest() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("cn.net.immortal");

        MyAnnotationBean myAnnotationBean = context.getBean(MyAnnotationBean.class);
        assert "my AnnotationBean".equals(myAnnotationBean.getName());
        //AnnotationConfigApplicationContext并不会主动加载xml?
//        MyXmlBean myXmlBean = context.getBean(MyXmlBean.class);
//        assert "my xmlBean".equals(myXmlBean.getName());
    }

    /**
     * xml配置方式
     */
    @Test
    public void classPathXmlApplicationContextTest() {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");
        MyXmlBean myXmlBean = context.getBean(MyXmlBean.class);
        assert "my xmlBean".equals(myXmlBean.getName());
        //xml 没有配置component-scan 不会扫描
//        MyAnnotationBean myAnnotationBean = context.getBean(MyAnnotationBean.class);
//        assert "my AnnotationBean".equals(myAnnotationBean.getName());
    }

    /**
     * xml绝对路径方式
     */
    @Test
    public void fileSystemXmlApplicationContextTest() {

        FileSystemXmlApplicationContext context =
                new FileSystemXmlApplicationContext(
                        "C:\\Java\\workspace\\daliy\\immortal\\spring\\src\\main\\resources\\spring.xml");
        MyXmlBean myXmlBean = context.getBean(MyXmlBean.class);
        assert "my xmlBean".equals(myXmlBean.getName());
    }

    /**
     * 嵌入式上下文，spring boot环境，这个工程里没有
     */
    @Test
    public void embeddedWebApplicationContextTest(){

    }
}
