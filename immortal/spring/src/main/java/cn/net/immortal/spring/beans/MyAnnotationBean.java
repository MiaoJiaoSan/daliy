package cn.net.immortal.spring.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyAnnotationBean {

    private String name = "my AnnotationBean";
}
