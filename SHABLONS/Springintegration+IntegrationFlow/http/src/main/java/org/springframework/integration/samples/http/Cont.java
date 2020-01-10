package org.springframework.integration.samples.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cont {
	@Autowired
	private Gataway gat;

	@CrossOrigin
	@PostMapping(value =  "/test",produces = "application/json")
	public String test() {
		System.out.println("GET REQUEST");
		return "{'New Data':10}";
	}
	
}
