package org.springframework.integration.samples.http;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class Serv {
	@ServiceActivator(inputChannel = "integration.gateway.channel23",outputChannel ="integration.gateway.channel2" )
	public void mes(Message<String> mes) {
		MessageChannel ch = (MessageChannel) mes.getHeaders().getReplyChannel();
		
		Message<String> n = MessageBuilder.withPayload(mes.getPayload() + " WITH").build();
		System.out.println("NONINTEGR");
		ch.send(n);
	}
	
	@ServiceActivator(inputChannel = "integration.gateway.channel2")
	public void mes2(Message<String> mes) {
		System.out.println("INTEGRATED");
		MessageChannel ch =(MessageChannel)mes.getHeaders().getReplyChannel();
		
		Message<String> n = MessageBuilder.withPayload(mes.getPayload()+" WITH").build();
		ch.send(n);
	}
	@ServiceActivator(inputChannel = "ch1")
	public void mes23(Message<String> mes) {
		MessageChannel ch = (MessageChannel) mes.getHeaders().getReplyChannel();
		
		Message<String> n = MessageBuilder.withPayload(mes.getPayload() + " WITH").build();
		System.out.println("1");
		ch.send(n);
	}
	@ServiceActivator(inputChannel = "ch2")
	public void mes24(Message<String> mes) {
		MessageChannel ch = (MessageChannel) mes.getHeaders().getReplyChannel();
		
		Message<String> n = MessageBuilder.withPayload(mes.getPayload() + " WITH").build();
		System.out.println("1");
		ch.send(n);
	}
	@ServiceActivator(inputChannel = "integration.gateway.channel3")
	public void ZZES(Message<Character> mes) {
		
		MessageChannel ch = (MessageChannel) mes.getHeaders().getReplyChannel();
		System.out.println(mes.getPayload());
		Message<String> n = MessageBuilder.withPayload(mes.getPayload() + " WITH").build();
		System.out.println("1");
		ch.send(n);
	}
}
