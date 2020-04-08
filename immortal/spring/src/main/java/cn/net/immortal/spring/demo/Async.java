package cn.net.immortal.spring.demo;

import java.util.concurrent.*;

public class Async {

    private static final ExecutorService pool = Executors.newFixedThreadPool(3);


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(Async::a).start();
//        System.out.println("main");
    }

    public static void a() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        // 遍历线程组树，获取根线程组
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        // 激活的线程数加倍
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slackList = new Thread[estimatedSize];
        // 获取根线程组的所有线程
        int actualSize = topGroup.enumerate(slackList);
        // copy into a list that is the exact size
        Thread[] list = new Thread[actualSize];
        System.arraycopy(slackList, 0, list, 0, actualSize);
        System.out.println("Thread list size == " + list.length);
        for (Thread thread : list) {
            System.out.println(thread.getName());
        }
//        System.out.println("a");
    }

    public String b() {
        return "b";
    }

    public String c() {
        return "c";
    }
}
