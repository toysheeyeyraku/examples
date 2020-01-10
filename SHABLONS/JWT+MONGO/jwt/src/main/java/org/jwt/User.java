package org.jwt;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
@Data
@Document(collection = "users")
public class User implements UserDetails {
	@Id
	private String id;
	private List<Role> authorities;
	private String password;
	
	private String username ;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	

}
