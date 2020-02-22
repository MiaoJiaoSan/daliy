package cn.net.immortal.spring.beans;

import lombok.Data;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import(MyImportBeanDefinitionRegister.class)
@Component
@Data
public class MyAnnotationBean {

    private String name = "my AnnotationBean";
}
