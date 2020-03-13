package cn.net.immortal.spring.demo;

import java.util.*;
import java.util.concurrent.*;

/**
 * 合并请求
 */
public class MergeRequest {

    private static final ExecutorService pool = Executors.newFixedThreadPool(30);

    private static final ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor();

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(30);

    private static final MergeRequest mergeRequest = new MergeRequest();
    //存放请求的队列
    static BlockingQueue<Request> queue = new LinkedBlockingQueue();

    public static void init(){
        scheduled.scheduleAtFixedRate(() -> {
            int size = queue.size();
            if (size == 0) {
                return;
            }
            List<Request> requests = new ArrayList<>(size);
            //封装成批量请求参数
            for (int i = 0; i < size; i++) {
                // 移出队列，并返回。
                Request request = queue.poll();
                requests.add(request);
            }
            //发起请求 获取批量查询结果
            List<Map<String,String>> results = mergeRequest.deal(requests);
            requests.forEach(request -> {
                results.forEach(result -> {
                    String rs;
                    if(Objects.nonNull(rs = result.get(request.getArg()))){
                        request.getFuture().complete(rs);
                    }
                });
            });



        }, 0, 10, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        init();

        //模拟同来收到30个请求
        for(int i = 0; i< 30; i++){
            int t = i;
            pool.execute(() -> {
                try {
                    cyclicBarrier.await();
                    Request request = new Request(String.valueOf((int)(Math.random()*100)));
                    CompletableFuture<String> taskCompletableFuture = new CompletableFuture<>();
                    request.setFuture(taskCompletableFuture);
                    queue.add(request);

                    System.out.println(t +"::"+request.getArg()+"::"+request.getFuture().get());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
    }


    public static class Request{
        private String arg;
        private CompletableFuture<String> future;
        public Request(String args){this.arg = args;}
        public String getArg() {
            return arg;
        }
        public void setArg(String arg) {
            this.arg = arg;
        }
        public CompletableFuture<String> getFuture() {
            return future;
        }
        public void setFuture(CompletableFuture<String> future) {
            this.future = future;
        }
    }


    public List<Map<String,String>> deal(List<Request> list){
        List<Map<String,String>> results = new ArrayList<>();
        //冒充业务查询方法
        list.forEach(request -> {
            Map<String,String> map = new HashMap<>();
            map.put(request.getArg(),request.getArg());
            results.add(map);
        });

        return results;
    }


}
