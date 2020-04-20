package cn.net.immortal.spring.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnmodifiableList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list = Collections.unmodifiableList(list);
        list.add("c");
    }
}
