package org.springframework.integration.samples.http;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSelector;
import org.springframework.integration.filter.MessageFilter;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.integration.splitter.MethodInvokingSplitter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class Cfg {
	@Bean
	public MessageChannel recieverChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel replyChannel() {
		return new DirectChannel();
	}

	@Bean
	@Transformer(inputChannel = "integration.gateway.channel2", outputChannel = "integration.gateway.channel2")
	public Custom cst() {
		return new Custom();
	}

	class MessageModifir {
		public Collection<Object> s(String ss) {
			
			Collection<Object> n = new ArrayList<Object>();
			for (Character ch :ss.toCharArray()) {
				n.add(ch);
			}
			return n;

		}
	}

	@Splitter(inputChannel = "integration.gateway.channel")
	@Bean
	public AbstractMessageSplitter splitter() {
		MethodInvokingSplitter mis = new MethodInvokingSplitter(new MessageModifir(), "s");
		mis.setOutputChannelName("integration.gateway.channel2");
		return mis;
	}

	@Filter(inputChannel = "integration.gateway.channel")
	@Bean
	public MessageFilter filter() {
		MessageFilter filter = new MessageFilter(new MessageSelector() {
			@Override
			public boolean accept(Message<?> message) {
				return message.getPayload().toString().endsWith("rer");
			}
		});
		filter.setOutputChannelName("integration.gateway.channel");
		return filter;
	}
	/*
	 * @Bean
	 * 
	 * @ServiceActivator(inputChannel = "integration.gateway.channel") public
	 * RecipientListRouter router() { RecipientListRouter ro=new
	 * RecipientListRouter(); ro.addRecipient("ch1"); ro.addRecipient("ch2"); return
	 * ro; }
	 */

}
