package appp.timsnky.todo_full.controller;

import appp.timsnky.todo_full.model.Todo;
import appp.timsnky.todo_full.repository.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("")
    public String index(Model model)
    {
        List<Todo> todos = this.todoRepository.findAll();

        model.addAttribute("todos", todos);

        return "todos/index";
    }

    @GetMapping(value = {"/create", "/create/{id}"})
    public String create(@PathVariable(required = false) Integer id, Model model)
    {
        Todo todo = id == null ? new Todo() : this.todoRepository.findById(id).orElse(new Todo());

        model.addAttribute("todo", todo);

        return "todos/create";
    }

    @PostMapping(value = {"/store", "/store/{id}"})
    public String store(@PathVariable(required = false) Integer id, @Valid Todo todo, BindingResult result, HttpServletRequest request)
    {
        if (result.hasErrors()) {
            return "todos/create";
        }

        Principal principal = request.getUserPrincipal();

        todo.setUsername(principal.getName());

        this.todoRepository.save(todo);

        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id)
    {
        this.todoRepository.deleteById(id);

        return "redirect:/todos";
    }
}
