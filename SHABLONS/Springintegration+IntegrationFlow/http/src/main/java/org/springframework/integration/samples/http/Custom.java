package org.springframework.integration.samples.http;

public class Custom {
	public String transform(String data) {
		return "transformed "+data;
	}
}
