package cn.net.immortal.rabbit.cosumer;

import cn.net.immortal.rabbit.producer.NormalProducer;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class NormalConsumer {


    public void consumer() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("www.miaojiaosan.com");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(NormalProducer.EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(NormalProducer.QUEUE_NAME,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,null);
        channel.queueBind(NormalProducer.QUEUE_NAME,NormalProducer.EXCHANGE,NormalProducer.ROUTING_KEY);
        channel.basicConsume(NormalProducer.QUEUE_NAME,Boolean.FALSE,new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body, StandardCharsets.UTF_8);
                System.out.println("this is consumer1" + s +"time:" + System.currentTimeMillis());
//                channel.basicAck(envelope.getDeliveryTag(), Boolean.TRUE);
            }
        });

    }
}
