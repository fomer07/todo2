package com.example.todo.rest;


import com.example.todo.model.TodoItem;
import com.example.todo.model.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoItemControllerTest {

    private static final String TODO_ENDPOINT = "/api/todo/item";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    public String getToken()throws Exception{
       User user = new User();
       user.setUsername("test");
       user.setPassword("test");
        MvcResult mvcResult= mockMvc.perform(post("/login")
                .content(objectMapper.writeValueAsString(user))
                .contentType("application/json"))
                .andExpect(status().is(200)).andReturn();

        return mvcResult.getResponse().getHeader("token");
    }


    @Test
    public void createTodoItem() throws Exception{
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle("mockTest");
        todoItem.setDescription("mockTest");

        mockMvc.perform(post(TODO_ENDPOINT+"/new")
        .header("Authorization", getToken())
        .content(objectMapper.writeValueAsString(todoItem))
        .contentType("application/json")
        ).andExpect(status().is(200));
    }

    @Test
    public void getTodoItem() throws Exception{
        mockMvc.perform(get(TODO_ENDPOINT+"/{id}",2)
        .contentType("application/json")
        .header("Authorization", getToken()))
                .andExpect(status().is(200));
    }


    @Test
    public void getAllByUser() throws Exception{
        mockMvc.perform(get(TODO_ENDPOINT+"/all")
        .header("Authorization", getToken())
        .contentType("application/json"))
                .andExpect(status().is(200));
    }

    @Test
    public void changeIsDone() throws Exception{
        mockMvc.perform(put(TODO_ENDPOINT+"/edit/{id}",2)
        .header("Authorization", getToken())
        .contentType("application/json"))
                .andExpect(status().is(200));
    }

    @Test
    public void deleteTodoItem() throws Exception{
        mockMvc.perform(delete(TODO_ENDPOINT+"/delete/{id}",12)
        .contentType("application/json")
        .header("Authorization", getToken())
        ).andExpect(status().is(200))
        .andReturn().getResponse().getContentAsString().equals("true");

    }












}
