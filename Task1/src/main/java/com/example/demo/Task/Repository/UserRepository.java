package com.example.demo.Task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Task.Entity.User;
import com.example.demo.Task.Enum.UserRoles;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	User findByUserRoles(UserRoles userRoles);	
}
