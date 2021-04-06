package com.example.todo.services;

import com.example.todo.model.TodoItem;
import com.example.todo.repository.TodoItemRepository;
import com.example.todo.service.TodoItemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TodoItemServiceTest {

    @Mock
    private TodoItemRepository repository;

    @InjectMocks
    private TodoItemService service;

    private TodoItem mock = new TodoItem();

    @BeforeEach
    void init(){
        mock.setId(1L);
        mock.setTitle("mockito");
        mock.setDescription("test");
        mock.setDone(false);
        repository.save(mock);
    }

    @Test
    void saveTodoItemToRepo(){
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle("mockito");
        todoItem.setDescription("mockito");
        when(repository.save(any(TodoItem.class))).then(returnsFirstArg());
        TodoItem saved = service.saveWithoutId(todoItem);
        assertThat(saved.getCreated()).isNotNull();
    }

    @Test
    void findById(){
        when(repository.findById(mock.getId())).thenReturn(Optional.ofNullable(mock));
        assertThat(mock).isEqualTo(service.getOne(mock.getId()));

    }

    @Test
    void completedTest(){
        when(repository.findById(mock.getId())).thenReturn(Optional.ofNullable(mock));
        TodoItem completed = service.completed(mock.getId());
        assertThat(completed.isDone()).isTrue();
    }

    @Test
    void findAll(){
        List<TodoItem> mocks = new ArrayList<>();
        mocks.add(mock);
        when(repository.findAll()).thenReturn(mocks);
        List<TodoItem> list = service.getAllTodos();
        assertThat(list).size().isGreaterThanOrEqualTo(1);
    }



}
