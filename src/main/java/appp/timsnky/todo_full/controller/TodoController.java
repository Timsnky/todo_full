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

    @GetMapping("/create")
    public String create(Todo todo)
    {
        return "todos/create";
    }

    @PostMapping("/store")
    public String store(@Valid Todo todo, BindingResult result, HttpServletRequest request)
    {
        if (result.hasErrors()) {
            return "todos/create";
        }

        Principal principal = request.getUserPrincipal();

        todo.setUsername(principal.getName());

        this.todoRepository.save(todo);

        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@RequestParam long id)
    {

        return "todos/create";
    }

    @PostMapping("/update")
    public String update()
    {
        return "todos/index";
    }

    @GetMapping("/delete")
    public String delete()
    {
        return "todos/index";
    }
}
