package cn.net.immortal.spring.demo;

import org.junit.Test;

/**
 * 测试volatile可见性
 */
public class ThreadProblemTest {


    @Test
    public void noVolatileTest() throws Exception {
        ThreadProblem.NoVolatile noVolatileTest = new ThreadProblem.NoVolatile();
        noVolatileTest.start();
        Thread.sleep(1000);
        noVolatileTest.setFlag(Boolean.FALSE);
        System.in.read();
    }


    @Test
    public void volatileTest() throws Exception {
        ThreadProblem.Volatile volatileTest = new ThreadProblem.Volatile();
        volatileTest.start();
        Thread.sleep(1000);
        volatileTest.setFlag(Boolean.FALSE);
        System.in.read();
    }
}
