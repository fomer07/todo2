package com.example.todo.controller;

import com.example.todo.dto.RegisterRequest;
import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class AuthController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest request){
        User user = new User(request.getUsername(),
               bCryptPasswordEncoder.encode(request.getPassword()),
                request.getEmail());

        userRepository.save(user);

        return new ResponseEntity(HttpStatus.CREATED);

    }




    }






