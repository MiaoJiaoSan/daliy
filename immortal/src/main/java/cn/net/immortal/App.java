package cn.net.immortal;

import cn.net.immortal.beans.MyBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("cn.net.immortal");

        MyBean bean = context.getBean(MyBean.class);
        System.out.println(bean.getName());
    }
}
