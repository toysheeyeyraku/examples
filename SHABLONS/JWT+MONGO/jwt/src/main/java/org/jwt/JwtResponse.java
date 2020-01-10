package org.jwt;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private String jwttoken;
	private String refreshtoken;

	public JwtResponse(String jwttoken, String refresh) {
		this.jwttoken = jwttoken;
		this.refreshtoken = refresh;
	}

}