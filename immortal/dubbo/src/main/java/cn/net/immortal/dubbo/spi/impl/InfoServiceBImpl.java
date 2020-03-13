package cn.net.immortal.dubbo.spi.impl;

import cn.net.immortal.dubbo.service.DubboService;
import cn.net.immortal.dubbo.spi.InfoService;
import com.alibaba.dubbo.common.extension.Activate;
import org.springframework.beans.factory.annotation.Autowired;

@Activate
public class InfoServiceBImpl implements InfoService {

    DubboService dubboService;

    @Override
    public void sayHello() {
        System.out.println("hello, there is B implement");
        dubboService.dubboMethod();
    }

    public void setDubboService(DubboService dubboService) {
        this.dubboService = dubboService;
    }
}
