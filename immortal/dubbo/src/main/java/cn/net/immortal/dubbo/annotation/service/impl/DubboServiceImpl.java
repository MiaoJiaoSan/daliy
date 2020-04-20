package cn.net.immortal.dubbo.annotation.service.impl;

import cn.net.immortal.dubbo.annotation.service.DubboService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class DubboServiceImpl implements DubboService {

    @Override
    public void sayHello() {
        System.out.println("Hello, there is dubbo service");
    }
}
