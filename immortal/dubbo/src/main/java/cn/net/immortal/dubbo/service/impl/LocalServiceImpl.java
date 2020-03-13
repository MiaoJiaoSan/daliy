package cn.net.immortal.dubbo.service.impl;

import cn.net.immortal.dubbo.service.LocalService;
import org.springframework.stereotype.Service;

@Service
public class LocalServiceImpl implements LocalService {
    @Override
    public void localMethod() {
        System.out.println("local method");
    }
}
