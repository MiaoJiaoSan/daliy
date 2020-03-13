package cn.net.immortal.dubbo.spi.impl;

import cn.net.immortal.dubbo.service.LocalService;
import cn.net.immortal.dubbo.spi.InfoService;

public class InfoServiceAImpl implements InfoService {

    private LocalService localService;

    @Override
    public void sayHello() {
        System.out.println("hello, there is A implement");
        localService.localMethod();
    }

    public void setLocalService(LocalService localService) {
        this.localService = localService;
    }
}
