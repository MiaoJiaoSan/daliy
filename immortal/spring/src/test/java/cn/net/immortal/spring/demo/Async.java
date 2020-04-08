package cn.net.immortal.spring.demo;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Async {

    @Test
    public void a() throws ExecutionException, InterruptedException {
        new Thread(Async::b).start();
        System.out.println("main");
    }

    public static void b() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a");
    }

}
