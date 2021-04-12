package com.gucarsoft.sanalkutuphane.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    //@PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','USER')")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/test")
    public ResponseEntity workingTest(){
        return new ResponseEntity("Merhaba",HttpStatus.OK);
    }

}
