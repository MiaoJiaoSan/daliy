package cn.net.immortal.dubbo.spi;

import com.alibaba.dubbo.common.extension.SPI;

@SPI("a")
public interface InfoService {

    void sayHello();
}
