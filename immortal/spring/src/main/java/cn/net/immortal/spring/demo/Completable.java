package cn.net.immortal.spring.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * @date: 2020/4/21
 */
public class Completable {


    static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {


        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1");
        },executor).whenComplete((r,t) -> {
            System.out.println("2");
        }).get();

    }


}
