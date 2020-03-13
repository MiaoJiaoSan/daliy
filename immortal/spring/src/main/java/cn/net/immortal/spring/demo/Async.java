package cn.net.immortal.spring.demo;

import java.util.concurrent.*;

public class Async {

    private static final ExecutorService pool = Executors.newFixedThreadPool(3);


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Async async = new Async();
        Future<String> a = pool.submit(async::a);
        Future<String> b = pool.submit(async::b);
        Future<String> c = pool.submit(async::c);
        System.out.println(a.get()+b.get()+c.get());
        pool.shutdown();
    }

    public  String a(){
        return "a";
    }

    public  String b(){
        return "b";
    }

    public  String c(){
        return "c";
    }
}
