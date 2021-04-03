package com.example.todo.service;

import com.example.todo.model.TodoItem;
import com.example.todo.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    public TodoItem save(TodoItem todoItem){
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

    public List<TodoItem> findAll(){
        return todoItemRepository.findAll();
    }

    public TodoItem getOne(Long id){
        return todoItemRepository.findById(id).get();
    }



}
