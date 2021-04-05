package com.example.todo;

import com.example.todo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface TestBeanProvider {

    UserRepository getUserRepository();
    BCryptPasswordEncoder getBCryptPasswordEncoder();



}
