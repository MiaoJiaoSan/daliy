package cn.net.immortal.spring.demo;

import cn.net.immortal.spring.scope.Starter;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;


public class TestScope {

    @Test
    public void refresh() throws Exception{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Starter.class);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        System.out.println(context.getBean(Starter.class).getPort());
        ConfigurableEnvironment environment = context.getEnvironment();
//        System.out.println(environment.getProperty("server.port"));
        MutablePropertySources propertySources = environment.getPropertySources();

        ResourcePropertySource resourcePropertySource = new ResourcePropertySource("classpath:/test.properties");
        propertySources.replace("class path resource [application.properties]",resourcePropertySource);
//        Iterator<PropertySource<?>> iterator = propertySources.iterator();
//        while(iterator.hasNext()){
//            PropertySource<?> next = iterator.next();
//            Object property = next.getProperty("server.port");
//            if(Objects.nonNull(property)){
//                System.out.println(property);
//            }
//        }
        Scope refresh = beanFactory.getRegisteredScope("refresh");
        refresh.remove("starter");
        System.out.println(context.getBean(Starter.class).getPort());
    }
}
