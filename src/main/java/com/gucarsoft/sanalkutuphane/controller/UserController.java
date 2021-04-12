package com.gucarsoft.sanalkutuphane.controller;

import com.gucarsoft.sanalkutuphane.helper.JwtUtil;
import com.gucarsoft.sanalkutuphane.model.user.JwtRequestModel;
import com.gucarsoft.sanalkutuphane.repository.UserRepository;
import com.gucarsoft.sanalkutuphane.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody JwtRequestModel jwtRequestModel) {
        String generatedToken = "";
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestModel.getUsername(), jwtRequestModel.getPassword()));
            UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequestModel.getUsername());
            generatedToken = jwtUtil.generateToken(userDetails);
            return  new ResponseEntity(generatedToken,HttpStatus.OK);
        } catch (AuthenticationException ex) {
            return new ResponseEntity("user not found!",HttpStatus.NOT_FOUND);
        }
    }

}
