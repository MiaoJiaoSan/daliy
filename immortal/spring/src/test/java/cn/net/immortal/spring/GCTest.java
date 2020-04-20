package cn.net.immortal.spring;

import cn.net.immortal.spring.gc.GarbageCollectionComponent;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * @date: 2020/4/20
 */
public class GCTest {

    @Test
    public void geTest() throws IOException {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(GarbageCollectionComponent.class);
        context.start();
        System.in.read();
    }
}
