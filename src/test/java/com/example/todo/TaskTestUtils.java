package com.example.todo;

import com.example.todo.model.TodoItem;
import com.example.todo.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class TaskTestUtils {


    public static User createMockUser(){

        User user = new User();
        user.setUsername("mock");
        user.setPassword("mock");
        user.setEmail("mail@mail.com");
        return user;

    }

    public static String crateMockUserJson() throws JsonProcessingException{
        return new ObjectMapper().writeValueAsString(createMockUser());
    }

    public static TodoItem createMockTodo(Long userId){
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle("mockTest");
        todoItem.setDescription("mockTest");
        todoItem.setUserId(userId);
        return todoItem;
    }

    public static List<TodoItem> create50Todo(Long userId){
        List<TodoItem> todos = new ArrayList<>();
        for (int i = 0;i<50;i++){
            TodoItem todoItem = createMockTodo(userId);
            todos.add(todoItem);
        }
        return todos;
    }


    public static User saveMockUserToRepo(TestBeanProvider provider){
        User user = createMockUser();
        user.setPassword(provider.getBCryptPasswordEncoder().encode(user.getPassword()));
        return provider.getUserRepository().save(user);
    }




}
