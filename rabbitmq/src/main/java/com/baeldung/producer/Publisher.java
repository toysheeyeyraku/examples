package com.baeldung.producer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {

    private final static String QUEUE_NAME = "products_queue";

    public static void main(String[]args) throws IOException, TimeoutException {
    	String exchangeName="A";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
       // channel.exchangeDelete(exchangeName);
        //channel.exchangeDeclare(exchangeName, "fanout", true);
      //  channel.queueDeclare(QUEUE_NAME, false, false, false, null);
       //channel.queueBind(QUEUE_NAME, exchangeName, "B");
        String message = "delete play for rolland";
        

        channel.basicPublish("test","delete",null,message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
