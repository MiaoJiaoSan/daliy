package cn.net.immortal.spring.demo;

import jdk.nashorn.internal.runtime.options.Option;

import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistencyHash {

    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111",

            "192.168.0.4:111"};

    private static TreeMap<Integer, String> treeMap = new TreeMap<>();

    static {
        for (String server : servers) {

            int hash = getHash(server);
            System.out.println("[" + server + "]加入集合中, 其Hash值为" + hash);
            treeMap.put(hash, server);
        }
    }

    static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;

    }

    static String getServer(String node) {
        int hash = getHash(node);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = treeMap.tailMap(hash);
        if (subMap.size() <= 0) {
            subMap = treeMap;
        }
        // 第一个Key就是顺时针过去离node最近的那个结点
        Integer i = subMap.firstKey();
        // 返回对应的服务器名称
        return subMap.get(i);
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333", "222.213.13.23:2323", "223.213.34.67:2341"};
        for (String node : nodes)
            System.out

                    .println("[" + node + "]的hash值为" + getHash(node) + ", 被路由到结点[" + getServer(node) + "]");

    }
}
