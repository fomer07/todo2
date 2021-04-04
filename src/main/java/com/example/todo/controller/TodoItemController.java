package com.example.todo.controller;

import com.example.todo.model.TodoItem;
import com.example.todo.service.TodoItemService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class TodoItemController {

    private final TodoItemService todoItemService;

    @GetMapping("/item/{id}")
    public TodoItem getOne(@PathVariable Long id){
        return todoItemService.getOne(id);
    }

    @GetMapping("/item/all")
    public List<TodoItem> getAll(){
        return todoItemService.findAll();
    }

    @PostMapping("/item/new")
    public ResponseEntity<TodoItem> save(@RequestBody TodoItem item){
        return ResponseEntity.ok(todoItemService.save(item));
    }

    @PutMapping("/item/edit/{id}")
    public ResponseEntity<TodoItem> completed(@PathVariable Long id){
        return ResponseEntity.ok(todoItemService.completed(id));
    }

    @DeleteMapping("/item/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(todoItemService.delete(id));
    }


}
