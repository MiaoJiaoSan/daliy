package cn.net.immortal.spring.beans;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;


public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    /**
     * 首先ImportBeanDefinitionRegistrar只在@Import(MyImportBeanDefinitionRegister.class)时才会执行
     * 当前类是标注@Import的类
     * @Import在@Component之后被解析,也就是说标注@Import的类自己必须是一个Bean
     * @param importingClassMetadata 当前类注解信息
     * @param registry Beandifinition注册类
     *                 把所有需要添加到容器的bean加入
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        importingClassMetadata.getAnnotationTypes().forEach(System.out::println);
        System.out.println("MyImportBeanDefinitionRegister is running...");
//        registry.registerBeanDefinition("myImportBeanDefinitionRegister",new RootBeanDefinition(MyImportBeanDefinitionRegister.class));
    }
}
