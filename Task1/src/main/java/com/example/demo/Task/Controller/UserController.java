package com.example.demo.Task.Controller;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Task.Entity.User;
import com.example.demo.Task.Services.UserService;
import com.example.demo.Task.Util.LoginResponse;
@CrossOrigin("http://localhost:4200")
@RestController
public class UserController {
@Autowired
private UserService userService;
	
@PostMapping("/register")
public User createUser(@RequestBody User user) throws Exception {
	return userService.createUser(user);

}

@PostMapping("/login")
public LoginResponse  verifyUser(@RequestBody User user) throws AuthenticationException {
	LoginResponse loginResponse = userService.verifyUser(user);
	System.out.println(loginResponse );
	return loginResponse;
    
}

@GetMapping("/get")
public List<User>getAll(){
	return userService.getAll();
}
}
