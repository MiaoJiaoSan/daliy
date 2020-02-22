package cn.net.immortal.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ImportBeanDefinitionRegistrarTest {


    @Test
    public void  myImportBeanDefinitionRegister(){
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("cn.net.immortal");
//        报错。并没有将实现ImportBeanDefinitionRegistrar的类作为一个bean
//        context.getBean(MyImportBeanDefinitionRegister.class);

    }
}
