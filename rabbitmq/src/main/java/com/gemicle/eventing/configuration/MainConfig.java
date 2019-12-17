 
package com.gemicle.eventing.configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class MainConfig {
	@Bean
	public Channel getChannel() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		return connection.createChannel();
	}
	@Bean 
	public DynamicEventingConsumer getdynamic() throws IOException, TimeoutException {
		DynamicEventingConsumer consumer=new DynamicEventingConsumer("test", "delete", getChannel());
		consumer.addEvent(new EventListener());
		return consumer;
	}
	
}
