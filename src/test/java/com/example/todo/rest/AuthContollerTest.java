package com.example.todo.rest;

import com.example.todo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthContollerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void signup() throws Exception{
        User user = new User();
        user.setUsername("mock");
        user.setPassword("mock");
        user.setEmail("mock");
        mockMvc.perform(post("/auth/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().is(201));
    }

    @Test
    public void login()throws Exception{
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        MvcResult mvcResult= mockMvc.perform(post("/login")
                .content(objectMapper.writeValueAsString(user))
                .contentType("application/json"))
                .andExpect(status().is(200)).andReturn();

        String token =mvcResult.getResponse().getHeader("token");

        mockMvc.perform(get("/auth/secured")
                .header("Authorization", token))
                .andExpect(status().is(200));

    }





}
