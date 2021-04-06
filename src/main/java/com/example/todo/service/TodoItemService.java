package com.example.todo.service;

import com.example.todo.model.TodoItem;

import com.example.todo.repository.TodoItemRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class TodoItemService {


    private final TodoItemRepository todoItemRepository;
    private final UserService userService;

    public TodoItem save(TodoItem todoItem,String token){
        todoItem.setUserId(userService.getCurrent(token).getId());
        return todoItemRepository.save(todoItem);
    }
    public TodoItem saveWithoutId(TodoItem todoItem){
        return todoItemRepository.save(todoItem);
    }

    public TodoItem completed(Long id){
        TodoItem todoItem = todoItemRepository.findById(id).get();

        if (todoItem !=null){
            todoItem.setDone(true);
            todoItemRepository.save(todoItem);
            return todoItem;
        }
        return null;
    }

    public Boolean delete(Long id){
        TodoItem todoItem = todoItemRepository.findById(id).get();

        if (todoItem != null){
            todoItemRepository.delete(todoItem);
            return true;
        }
        return false;
    }

    public List<TodoItem> findAll(String token){
        return todoItemRepository.findByUserId(userService.getCurrent(token).getId());
    }

    public List<TodoItem> getAllTodos(){
        return todoItemRepository.findAll();
    }

    public TodoItem getOne(Long id){
        return todoItemRepository.findById(id).get();
    }



}
