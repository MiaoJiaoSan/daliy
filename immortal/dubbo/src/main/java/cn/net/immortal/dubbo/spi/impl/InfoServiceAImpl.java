package cn.net.immortal.dubbo.spi.impl;

import cn.net.immortal.dubbo.service.LocalService;
import cn.net.immortal.dubbo.spi.InfoService;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;

@Activate(group = "group")
public class InfoServiceAImpl implements InfoService {

    private LocalService localService;

    @Override
    public void sayHello() {
        System.out.println("hello, there is A implement");
        localService.localMethod();
    }

    @Override
    public void adaptive(URL url) {

    }

    public void setLocalService(LocalService localService) {
        this.localService = localService;
    }
}
