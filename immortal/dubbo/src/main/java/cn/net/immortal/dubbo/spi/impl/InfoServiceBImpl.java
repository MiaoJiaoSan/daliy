package cn.net.immortal.dubbo.spi.impl;

import cn.net.immortal.dubbo.service.DubboService;
import cn.net.immortal.dubbo.spi.InfoService;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import org.springframework.beans.factory.annotation.Autowired;

@Activate(group = "group", value = "b")
public class InfoServiceBImpl implements InfoService {

    DubboService dubboService;

    @Override
    public void sayHello() {
        System.out.println("hello, there is B implement");
        dubboService.dubboMethod();
    }

    @Override
    public void adaptive(URL url) {
        System.out.println("hello, there is B implement");
    }

    public void setDubboService(DubboService dubboService) {
        this.dubboService = dubboService;
    }
}
