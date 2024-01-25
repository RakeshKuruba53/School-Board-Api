package com.School.sba.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.School.sba.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findByUserName(username).map(user-> new CustomUserdetails(user))
				.orElseThrow(()->new UsernameNotFoundException("Authenticate failed."));
	}

}
