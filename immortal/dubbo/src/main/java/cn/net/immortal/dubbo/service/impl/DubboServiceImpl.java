package cn.net.immortal.dubbo.service.impl;

import cn.net.immortal.dubbo.service.DubboService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class DubboServiceImpl implements DubboService {

    @Override
    public void dubboMethod() {
        System.out.println("dubbo method");
    }
}
