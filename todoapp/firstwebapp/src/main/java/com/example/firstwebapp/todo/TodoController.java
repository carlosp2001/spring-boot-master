package com.example.firstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

//@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Método para mostrar la lista de todos
    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        // Cuando querramos usar el username o cualquier información del usuario debemos obtener desde el contexto de
        // Spring Security
        String username = getName(model);
        List<Todo> todos = todoService.findByUsername(username);
        model.addAttribute("todos", todos);
        return "listTodos";
    }

    private static String getName(ModelMap model) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    // Método para mostrar la página para crear un nuevo todo
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String username = (String) model.get("name");
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }

    // Método para agregar un nuevo todo
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        String username = (String) model.get("name");
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);

        // En este caso debemos hacer redireccionamiento porque queremos popular los datos
        // no solo mostrar las vistas
        return "redirect:list-todos";
    }

    // Método para eliminar un todo
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        // Delete todo
        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    // Método para mostrar la página de actualización del todo
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    // Método para actualizar el todo
    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        String username = (String) model.get("name");
        todoService.updateTodo(todo);

        return "redirect:list-todos";
    }
}
