package cn.net.immortal.beans;

import org.springframework.stereotype.Component;

@Component
public class MyBean {

    private String name = "my bean";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
