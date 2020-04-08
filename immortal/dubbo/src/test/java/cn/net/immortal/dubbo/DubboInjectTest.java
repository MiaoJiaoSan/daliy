package cn.net.immortal.dubbo;

import cn.net.immortal.dubbo.spi.InfoService;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DubboInjectTest {


    @Test
    public void  injectTest(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(DubboConfiguration.class);
        annotationConfigApplicationContext.start();


        ExtensionLoader<InfoService> extensionLoader = ExtensionLoader.getExtensionLoader(InfoService.class);
        extensionLoader.getExtension("a").sayHello();
        extensionLoader.getExtension("b").sayHello();
    }
}
