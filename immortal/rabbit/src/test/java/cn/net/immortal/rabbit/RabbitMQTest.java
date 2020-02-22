package cn.net.immortal.rabbit;

import cn.net.immortal.rabbit.cosumer.NormalConsumer;
import cn.net.immortal.rabbit.cosumer.NormalConsumer2;
import cn.net.immortal.rabbit.producer.NormalProducer;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQTest {


    @Test
    public void producer() throws IOException, TimeoutException {
        new NormalProducer().producer();
    }

    @Test
    public void consumer() throws IOException, TimeoutException {
        new NormalConsumer().consumer();
        System.in.read();
    }

    @Test
    public void consumer2() throws IOException, TimeoutException {
        new NormalConsumer2().consumer();
        System.in.read();
    }
}
