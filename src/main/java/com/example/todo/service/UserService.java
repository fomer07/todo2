package com.example.todo.service;

import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.todo.security.SecurityConstants.*;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class UserService {

    private final UserRepository userRepository;


    public User getCurrent (String token){
        String username = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userRepository.findByUsername(username);
    }




}
