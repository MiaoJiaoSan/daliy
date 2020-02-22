package cn.net.immortal.rmi.client;

import org.junit.Test;

import java.sql.SQLOutput;

public class RmiClientTest {


    @Test
    public void rmiClientTest() throws Exception {
        RmiClient rmiClient = new RmiClient();
        rmiClient.invoke();
    }

    public static void main(String[] args) {

        Integer a = 10086;
        Integer b = 10010;
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        swap(a, b);
    }

    public static void swap(Integer a, Integer b){
        a^=b;
        b^=a;
        a^=b;
        System.out.println(System.identityHashCode(b));
    }
}
