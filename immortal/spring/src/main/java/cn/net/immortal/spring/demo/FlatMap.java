package cn.net.immortal.spring.demo;

import java.util.ArrayList;
import java.util.stream.Stream;

public class FlatMap {

    public static void main(String[] args) {
        new ArrayList<String>(){{
            add("aaaa");
            add("aaaa bbbb");
            add("aaaa bbbb cccc");
        }}.stream().flatMap(s -> Stream.of(s.split(" "))).forEach(System.out::println);


    }
}
