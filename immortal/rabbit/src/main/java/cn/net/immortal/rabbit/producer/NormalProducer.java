package cn.net.immortal.rabbit.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

public class NormalProducer {

    public static final String EXCHANGE = "default_exchange";
    public static final String ROUTING_KEY = "testSync";
    public static final String QUEUE_NAME = "test";

    public void producer() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("www.miaojiaosan.com");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT);
        String[] messages = new String[]{"testSync","testSync2","testSync3","testSync4","testSync5"};
        Stream.of(messages).forEach(s -> {
            try {
                channel.basicPublish(EXCHANGE,ROUTING_KEY,Boolean.FALSE,null,s.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        channel.close();
        connection.close();

    }
}
