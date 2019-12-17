


package com.gemicle.eventing.configuration;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class BasicConsumer implements Consumer {
	private DynamicEventingConsumer consumer;
	 public BasicConsumer(DynamicEventingConsumer consumer) {
		this.consumer=consumer;
	}
	@Override
	public void handleCancel(String arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleCancelOk(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleConsumeOk(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleDelivery(String arg0, Envelope arg1, BasicProperties arg2, byte[] arg3) throws IOException {
		String a=new String(arg3);
		
		consumer.processMessage(a);
	}

	@Override
	public void handleRecoverOk(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleShutdownSignal(String arg0, ShutdownSignalException arg1) {
		// TODO Auto-generated method stub
		
	}

}
