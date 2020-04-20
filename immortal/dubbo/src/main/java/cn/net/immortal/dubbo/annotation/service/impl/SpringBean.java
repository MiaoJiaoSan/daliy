package cn.net.immortal.dubbo.annotation.service.impl;

import cn.net.immortal.dubbo.annotation.service.DubboService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;


@Service
public class SpringBean {

    @Reference
    private DubboService dubboService;

    public void sayHello(){
        dubboService.sayHello();
    }
}
