package com.gemicle.eventing.configuration;

import java.io.IOException;

import com.rabbitmq.client.Channel;

public class DynamicEventProducer {
	private String excahngeName ;
	private String eventName ;
	private Channel channel ;
	public DynamicEventProducer(String exchangeName,String eventName,Channel channel) {
		this.eventName=eventName;
		this.excahngeName=exchangeName;
		this.channel=channel;
		
	}
	public void invkeEvent(String message) throws IOException {
		System.out.println(this.excahngeName);
		channel.basicPublish(excahngeName,eventName,null,message.getBytes());
		
	}
}
