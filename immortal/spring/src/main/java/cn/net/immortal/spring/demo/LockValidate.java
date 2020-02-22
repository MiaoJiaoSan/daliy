package cn.net.immortal.spring.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 异步转同步
 */
public class LockValidate {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private ExecutorService pool = Executors.newSingleThreadExecutor();

    public void firstSignal() {

        lock.lock();
        try {
            pool.execute(() -> {
//                System.out.println("");
                lock.lock();
                try {
                    condition.signal();
                } finally {
                    lock.unlock();
                }
            });
            condition.await();
            System.out.println("exit lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
