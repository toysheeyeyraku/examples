package org.jwt;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tokens")
public class Tokens {
	@org.springframework.data.annotation.Id
	
	private String Id;
	private String user;
	private String refreshToken ;
	private String token ;
	
}
