package cn.net.immortal.spring.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyBean {

    private String name = "my bean";
}
