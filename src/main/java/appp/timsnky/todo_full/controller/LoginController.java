package appp.timsnky.todo_full.controller;

import appp.timsnky.todo_full.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(User user)
    {
        return "auth/login";
    }
}
