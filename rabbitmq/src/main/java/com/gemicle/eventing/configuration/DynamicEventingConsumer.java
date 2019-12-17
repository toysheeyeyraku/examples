package com.gemicle.eventing.configuration;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.rabbitmq.client.Channel;
import com.sun.javadoc.Type;

public class DynamicEventingConsumer {	
	private BasicConsumer consumer;
	private String queueName;
	private String exchangeName;
	private String eventName;
	private Channel channel;
	private EventBus events = new EventBus();
	private List<Object> eventsA = new ArrayList<Object>();

	public DynamicEventingConsumer(String exchangeName, String eventName, Channel channel) throws IOException {
		this.channel = channel;
		queueName = "test" + eventName;
		channel.queueDeclare(queueName, false, false, false, null);
		channel.queueBind(queueName, exchangeName, eventName);
		consumer = new BasicConsumer(this);
		channel.basicConsume(queueName, true, consumer);
	}

	public  void processMessage(String data) throws JsonMappingException, JsonProcessingException {
		
		events.post(data);
		ObjectMapper mapper =new ObjectMapper();
		for (Class c :calculate()) {
			try {
				@SuppressWarnings("unchecked")
				Object d=mapper.readValue(data,  c);
				events.post(d);
			}catch(Exception exc) {
				
			}
		}
	}

	private Set<Class> calculate() {
		HashSet<Class> h = new HashSet<Class>();

		for (Object x : eventsA) {
			for (Method m : x.getClass().getMethods()) {
				if (m.isAnnotationPresent(Subscribe.class)) {
					if (m.getParameters().length == 1) {
						h.add((Class) m.getParameters()[0].getParameterizedType());
						
					}
				}
			}
		}
		return h;
	}

	public void addEvent(Object event) {
		events.register(event);
		eventsA.add(event);

	}
}
