package org.springframework.integration.samples.http;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface Gataway {
	@Gateway(requestChannel = "integration.gateway.channel")
	public String send(String s);
}
