package cn.net.immortal.dubbo.spi;

import cn.net.immortal.dubbo.annotation.DubboConfiguration;
import cn.net.immortal.dubbo.annotation.service.impl.SpringBean;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DubboServiceTest {

    @Test
    public void dubboServiceTest(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(DubboConfiguration.class);
        annotationConfigApplicationContext.start();
        SpringBean springBean = (SpringBean)annotationConfigApplicationContext.getBean("springBean");
        springBean.sayHello();
    }
}
