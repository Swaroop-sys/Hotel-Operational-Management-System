package com.example.demo.Task.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import com.example.demo.Task.Entity.MyCustomeUserDetails;
import com.example.demo.Task.Entity.User;
import com.example.demo.Task.Repository.UserRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			new UsernameNotFoundException("Username Not Found");
		}
		return new MyCustomeUserDetails(user);
	}

}
