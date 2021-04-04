package com.example.todo.service;

import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class AppUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) throw  new UsernameNotFoundException(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername()
        , user.getPassword(), Collections.emptyList() );
    }



}
