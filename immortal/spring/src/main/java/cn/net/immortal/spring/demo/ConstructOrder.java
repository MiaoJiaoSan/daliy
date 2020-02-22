package cn.net.immortal.spring.demo;

public class ConstructOrder {

    public static class A{
        public A() {
            System.out.println("AAAA");
        }
    }

    public static class B extends A{
        public B() {
            System.out.println("BBBB");
        }

        public B(String arg) {
            this();
            System.out.println(arg);
        }
    }

    public static void main(String[] args) {
        new B("arg");
    }
}
