package com.example.todo.repository;

import com.example.todo.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem,Long> {

    List<TodoItem> findByUserId(Long id);

}
