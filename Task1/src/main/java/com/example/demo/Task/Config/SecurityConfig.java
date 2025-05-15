package com.example.demo.Task.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.demo.Task.Enum.UserRoles;
import com.example.demo.Task.Filter.JwtFIlter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)  // Use this instead of @EnableGlobalMethodSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtFIlter jwtFIlter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,CorsConfigurationSource corsConfigurationSource) throws Exception {
	    return http
	            .cors(cors -> cors.configurationSource(corsConfigurationSource)) // Apply CORS configuration from CorsConfig
	    		.csrf(csrf -> csrf.disable())
	               .authorizeHttpRequests(request -> request
	                   .requestMatchers("/login","/register").permitAll()
	                   .requestMatchers("/get").hasAnyAuthority(UserRoles.ADMIN.name())
	                   .requestMatchers("/booking/save").hasAnyAuthority(UserRoles.CUSTOMER.name(),UserRoles.ADMIN.name())
	                   .requestMatchers("/booking/get").hasAnyAuthority(UserRoles.ADMIN.name())
	                   .requestMatchers("/food/userget").hasAnyAuthority(UserRoles.CUSTOMER.name())
	                   .requestMatchers("/food/get").hasAnyAuthority(UserRoles.ADMIN.name())
	                   .requestMatchers("/food/save").hasAnyAuthority(UserRoles.ADMIN.name())
	                   .anyRequest().authenticated()) // Any other request needs authentication
	               .httpBasic(Customizer.withDefaults())
	               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	               .addFilterBefore(jwtFIlter, UsernamePasswordAuthenticationFilter.class)
	               .build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
