package com.example.demo.Task.Services;

import java.util.List;
import java.util.Set;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Task.Entity.User;
import com.example.demo.Task.Enum.UserRoles;
import com.example.demo.Task.Repository.UserRepository;
import com.example.demo.Task.Util.LoginResponse;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	public User createUser(User user){
		user.setPassword(encoder.encode(user.getPassword()));
		user.setUserRoles(UserRoles.CUSTOMER);
		if(user.getUsername()==null) {
			System.out.println("Username Already Exists");
			return null;
		}
		return userRepository.save(user);
	}
	@PostConstruct
	public void createAdmin() {
		User adminUser = userRepository.findByUserRoles(UserRoles.ADMIN);
		if(adminUser==null) {
			User user = new User();
			user.setUsername("Admin");
			user.setPassword(new BCryptPasswordEncoder().encode("a@123"));
			user.setUserRoles(UserRoles.ADMIN);
			userRepository.save(user);
			
		}
	}
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public LoginResponse verifyUser(User user) throws AuthenticationException {
		org.springframework.security.core.Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		if(authentication.isAuthenticated()) {
	        User authenticatedUser = userRepository.findByUsername(user.getUsername());
			String token= jwtService.generateToken(user,authenticatedUser.getUserRoles());
	        UserRoles userRole = authenticatedUser.getUserRoles();
			System.out.println("Roooooolllllleeeee is"+userRole);
	        return new LoginResponse(token,user.getUsername(),userRole);
	        
		}
	    throw new AuthenticationException("Authentication failed");

	}

}
