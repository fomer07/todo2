package com.example.todo.model;


import lombok.Data;


import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "todos")

public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private Date created = new Date();

    private boolean isDone = false;

    private Long userId;


}
