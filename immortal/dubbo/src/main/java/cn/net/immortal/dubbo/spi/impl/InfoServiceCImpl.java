package cn.net.immortal.dubbo.spi.impl;

import cn.net.immortal.dubbo.spi.InfoService;
import com.alibaba.dubbo.common.extension.Adaptive;

@Adaptive
public class InfoServiceCImpl implements InfoService {
    @Override
    public void sayHello() {
        System.out.println("hello, there is C implement");
    }
}
