package org.serverside;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RefreshScope
@RestController
public class Cont {
	@Value("${a}")
	private String val;

	@GetMapping
	public String mes() {
		return val;
	}
}
