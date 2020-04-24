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

    public static final Executor exec = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> new ArrayList<String>() {{
            add("12123");
        }})
//                .thenAcceptAsync(System.out::println)
                .thenComposeAsync(d -> {
                    return getArrayListDouble(d);
                })
                .thenAcceptAsync(r -> {})
                .thenComposeAsync(i -> {
                    return getArrayListInteger(Collections.singletonList(i));
                })
                .thenAcceptAsync(r -> System.out.println(r));
//        System.out.println(voidCompletableFuture.get());
        System.in.read();
    }

    private static CompletableFuture<?> getArrayListDouble(List<?> s) {
//        System.out.println(Thread.currentThread().getId());
        return CompletableFuture.supplyAsync(() -> {
            List<Double> list = new ArrayList(s);
            list.add(1.0d);
            return list;
        });
    }

    private static CompletableFuture<?> getArrayListInteger(List<?> d) {
//        System.out.println(Thread.currentThread().getId());
        return CompletableFuture.supplyAsync(() -> {
            List<Integer> list = new ArrayList(d);
            list.add(1);
            return list;
        });
    }
}
