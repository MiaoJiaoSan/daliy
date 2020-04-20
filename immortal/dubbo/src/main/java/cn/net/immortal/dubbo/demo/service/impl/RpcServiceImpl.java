package cn.net.immortal.dubbo.demo.service.impl;

import cn.net.immortal.dubbo.demo.service.RpcService;
import org.springframework.stereotype.Service;

@Service
public class RpcServiceImpl implements RpcService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
