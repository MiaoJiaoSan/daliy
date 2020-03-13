package cn.net.immortal.dubbo.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.Set;

public class DubboTest {

    @Test
    public void dubboTest(){
        //需要接口标注@SPI注解
        ExtensionLoader<InfoService> infoServiceLoader = ExtensionLoader.getExtensionLoader(InfoService.class);
        InfoService infoService = infoServiceLoader.getExtension("a");
        infoService.sayHello();
        infoService = infoServiceLoader.getExtension("b");
        infoService.sayHello();
    }

    @Test
    public void dubboActiveTest(){
        //需要接口标注@SPI("a")注解 默认实现类 配置为a 否则getDefaultExtension()报错
        ExtensionLoader<InfoService> infoServiceLoader = ExtensionLoader.getExtensionLoader(InfoService.class);
        //类上标注@Activate的过滤(条件过滤)
        InfoService infoService = infoServiceLoader.getDefaultExtension();
        //获取一批@Active过滤条件的
        //infoServiceLoader.getActivateExtension()
        //类上标注@Adaptive
        infoService = infoServiceLoader.getAdaptiveExtension();
        //忽略@Adaptive标注的类
        Set<String> supportedExtensions = infoServiceLoader.getSupportedExtensions();
        supportedExtensions.forEach(System.out::println);
//        infoService.sayHello();
    }


}
