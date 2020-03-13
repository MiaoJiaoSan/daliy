package cn.net.immortal.dubbo.spi;

import org.junit.Test;

import java.util.ServiceLoader;

public class SpiTest {

    @Test
    public void spiTest(){
        ServiceLoader<InfoService> infoServices = ServiceLoader.load(InfoService.class);
        for(InfoService infoService: infoServices){
            infoService.sayHello();
        }
    }
}
