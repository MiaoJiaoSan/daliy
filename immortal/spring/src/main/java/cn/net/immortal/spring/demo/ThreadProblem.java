package cn.net.immortal.spring.demo;

/**
 * volatile 保证可见性
 * 验证这个问题的关键,不重新cpu调度
 */
public class ThreadProblem {

    public static class NoVolatile extends Thread {

        //没有使用volatile
        public boolean flag = Boolean.TRUE;;

        @Override
        public void run() {
            System.out.println("flag is " + flag + " task start...");
            while (flag) {
//                这两种情况本地内存刷入主内存
//                Thread.sleep(1000);
//                synchronized (this){}
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
}
