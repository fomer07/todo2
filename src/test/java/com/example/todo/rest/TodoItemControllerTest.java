package com.example.todo.rest;


import com.example.todo.TestBeanProvider;
import com.example.todo.config.SecurityConfiguration;
import com.example.todo.model.TodoItem;
import com.example.todo.model.User;
import com.example.todo.repository.TodoItemRepository;
import com.example.todo.service.TodoItemService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.example.todo.TaskTestUtils.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        mockMvc.perform(delete(TODO_ENDPOINT+"/delete/{id}",10)
        .contentType("application/json")
        .header("Authorization", getToken())
        ).andExpect(status().is(200))
        .andReturn().getResponse().getContentAsString().equals("true");

    }












}
