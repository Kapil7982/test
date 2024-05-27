package com.streamingapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.streamingapp.config.JwtUtil;
import com.streamingapp.models.AuthRequest;
import com.streamingapp.models.User;
import com.streamingapp.repositories.UserRepository;
import com.streamingapp.services.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

 @Autowired
 private AuthenticationManager authenticationManager;

 @Autowired
 private CustomUserDetailsService userDetailsService;

 @Autowired
 private JwtUtil jwtUtil;

 @Autowired
 private UserRepository userRepository;

 @Autowired
 private PasswordEncoder passwordEncoder;

 @PostMapping("/authenticate")
 public String createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
     authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
     );

     final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
     return jwtUtil.generateToken(userDetails);
 }

 @PostMapping("/register")
 public String registerUser(@RequestBody User user) {
     user.setPassword(passwordEncoder.encode(user.getPassword()));
     userRepository.save(user);
     return "User registered successfully";
 }
}



