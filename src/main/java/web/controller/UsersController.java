package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.model.User;


@Controller
public class UsersController {

    @Autowired
    private UserDAO userdao;

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userdao.getAllUser());
        return "users/index";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userdao.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String showUsersById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userdao.getUserById(id));
        return "users/show";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userdao.getUserById(id));
        User user1 = (User) model.getAttribute("user");
        System.out.println(id + " " + user1.getName());
        return "users/edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        System.out.println("edit " + id + " " + user.getName());
        userdao.editUser(id, user);
        return "redirect:/users";
    }

}
