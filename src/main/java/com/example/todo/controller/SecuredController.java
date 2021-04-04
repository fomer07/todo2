package com.example.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/secured")

public class SecuredController {

    //here is only for test for jwt authentication
    @GetMapping
    public ResponseEntity reachSecureEndpoint(){
        return new ResponseEntity("if you are reading this you have reached out secure point", HttpStatus.OK);
    }



}
