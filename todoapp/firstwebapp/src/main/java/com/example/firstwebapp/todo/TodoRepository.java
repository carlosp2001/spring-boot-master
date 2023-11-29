package com.example.firstwebapp.todo;

import com.example.firstwebapp.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepository extends JpaRepository <Todo, Integer >{
    // Solo debemos proporcionar el valor del parametro
    public List<Todo>  findByUsername(String username);
}
