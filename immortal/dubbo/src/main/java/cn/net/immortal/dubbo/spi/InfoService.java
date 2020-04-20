package cn.net.immortal.dubbo.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

@SPI()
public interface InfoService {


    void sayHello();

    @Adaptive
    void adaptive(URL url);
}
