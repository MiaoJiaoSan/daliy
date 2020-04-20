package cn.net.immortal.dubbo.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * SPI 时dubbo 会加载javaSPI的配置，如果配置了相同的类 会重复调用
 */
public class DubboTest {

    @Test
    public void dubboTest(){
        //需要接口标注@SPI注解
        //创建了一个InfoService的ExtensionLoader并缓存  ExtensionLoader 中设置了ExtensionFactory
        //ExtensionFactory设置了只读的 dubbo容器跟spring 容器
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


    @Test
    public void dubboActivateTest(){
        //需要接口标注@SPI("a")注解 默认实现类 配置为a 否则getDefaultExtension()报错
        ExtensionLoader<InfoService> infoServiceLoader = ExtensionLoader.getExtensionLoader(InfoService.class);
        URL url = URL.valueOf("test://localhost/test");
        url = url.addParameter("b", "1111");
        //第三个参数group
        //过滤器不需要meta-info services跟dubbo配置相同会重复调用
        List<InfoService> infoServices = infoServiceLoader.getActivateExtension(url,"","group");
        infoServices.forEach(InfoService::sayHello);
    }


    @Test
    public void dubboActivateTest2(){
        //需要接口标注@SPI("a")注解 默认实现类 配置为a 否则getDefaultExtension()报错
        ExtensionLoader<InfoService> infoServiceLoader = ExtensionLoader.getExtensionLoader(InfoService.class);
        InfoService adaptiveExtension = infoServiceLoader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test?info.service=b");
        adaptiveExtension.adaptive(url);

    }


}
