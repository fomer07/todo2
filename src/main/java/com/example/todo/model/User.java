package com.example.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String email;

    @OneToMany
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private List<TodoItem> todos;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
