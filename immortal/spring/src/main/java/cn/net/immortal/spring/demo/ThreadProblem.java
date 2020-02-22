package cn.net.immortal.spring.demo;

import java.util.concurrent.TimeUnit;

/**
 * volatile 保证可见性
 * 验证这个问题的关键,不从主内存读取共享变量
 */
public class ThreadProblem {

    public static class NoVolatile extends Thread {

        //没有使用volatile
        public boolean flag = Boolean.TRUE;;

        @Override
        public void run() {
            System.out.println("flag is " + flag + " task start...");
            while (flag) {
//                这两种情况读主内存
//                Thread.sleep(1000);
//                synchronized (this){}
//                synchronized内存语义:
//                当线程释放锁时，JMM会把该线程对应的本地内存中的共享变量刷新到主内存中。。
//                当线程获取锁时，JMM会把该线程对应的本地内存置为无效。从而使得被监视器保护的临界区代码必须从主内存中读取共享变量。
//                System.out.println("running...");
            }
            System.out.println("flag is " + flag + " task end...");
        }

        public void setFlag(boolean flag) {
            System.out.println("setting");
            synchronized (this){}
            this.flag = flag;
        }
    }

    public static class Volatile extends Thread {
        //没有使用volatile
        private volatile boolean flag = Boolean.TRUE;

        @Override
        public void run() {
            System.out.println("flag is " + flag + " task start...");
            while (flag) {}
            System.out.println("flag is " + flag + " task end...");
        }

        public void setFlag(boolean flag) {
            System.out.println("setting");
            this.flag = flag;
        }
    }

    public static class VolatileDemo {
        /*volatile*/ boolean flag = true;
        void demo(){
            System.out.println("start....");
            while (flag){

            }
            System.out.println("end.....");
        }
        public static void main(String[] args) {
            VolatileDemo data = new VolatileDemo();
            new Thread(()->data.demo(),"A").start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(()->data.flag = false,"B").start();
//            data.flag = false;
            Thread.interrupted();
        }
    }
}
