package cn.net.immortal.spring.demo;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FullSort {

    public static void main(String[] args) {
        List<String> src = new ArrayList<String>() {{
            add("甲");
            add("乙");
            add("丙");
            add("丁");
        }};

        List<List<String>> group = group(src);
        List<List<List<String>>> collect = group.stream().flatMap(strList -> {
            List<List<String>> result = new ArrayList<>();
            fullSort(strList,0, strList.size() - 1,result);
//            result.add(strList);
            return Stream.of(result);

        }).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println(collect.size() * collect.get(0).size());
    }

    private static List<List<String>> group(List<String> src) {
        int size = src.size();
        List<List<String>> group = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<String> list = new ArrayList<>(src);
            list.remove(i);
            group.add(list);
        }
        return group;
    }

    public static void fullSort(List<String> sortList, int start, int end,List<List<String>> result) {
        // 递归终止条件
        if (start == end) {
            result.add(new ArrayList<>(sortList));
        }
        for (int i = start; i <= end; i++) {
            swap(sortList, i, start);
            fullSort(sortList, start + 1, end,result);
            swap(sortList, i, start);
        }
    }

    private static void swap(List<String> sortList, int i, int j) {
        String t = sortList.get(i);
        sortList.set(i,sortList.get(j));
        sortList.set(j,t);;
    }

}
