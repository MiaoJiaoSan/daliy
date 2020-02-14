package cn.net.immortal.spring;

import static org.junit.Assert.*;

import cn.net.immortal.spring.beans.MyBean;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void springSourceTest()
    {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("cn.net.immortal");

        MyBean bean = context.getBean(MyBean.class);
        assertEquals("normal", bean.getName(),"my bean");
    }
}
