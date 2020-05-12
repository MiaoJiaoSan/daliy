package cn.net.immortal.spring.scope;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

@ComponentScan
@Data
@Scope("refresh")
public class Starter {

    @Value("${server.port}")
    private String port;


}
