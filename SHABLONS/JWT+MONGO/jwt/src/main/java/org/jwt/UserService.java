package org.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository rep;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {

		Optional<User> w = rep.findByUsername(arg0);
		if (w.isPresent()) {
			return w.get();
		} else {
			return new User();
		}

	}

}
