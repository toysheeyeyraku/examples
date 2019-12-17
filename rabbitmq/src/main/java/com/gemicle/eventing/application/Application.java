package com.gemicle.eventing.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemicle.eventing.configuration.A;
import com.gemicle.eventing.configuration.DynamicEventProducer;
import com.rabbitmq.client.Channel;
@SpringBootApplication(scanBasePackages = {"com.gemicle.eventing.configuration","com.gemicle.eventing.instances"})
public class Application implements CommandLineRunner {
	private static final String QUEUE_NAME = "products_queue1";
	@Autowired
	private Channel channel;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DynamicEventProducer producer =new DynamicEventProducer("test", "delete", channel);
		A a =new A();
		a.X=10;
		ObjectMapper m =new ObjectMapper();
		
		producer.invkeEvent(m.writeValueAsString(a));

        
		
	}
}
