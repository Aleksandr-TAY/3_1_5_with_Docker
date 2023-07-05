package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.models.User;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //Показать всех пользователей
    @GetMapping
    public List<User> showAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }
//    @GetMapping
//    public String showAllUsers(@ModelAttribute("user") User user, ModelMap model, Principal principal) {
//        model.addAttribute("users", userService.getAllUsers());
//        User authUser = userService.findByUsername(principal.getName());
//        model.addAttribute("user", authUser);
//        List<User> allUsersList = userService.getAllUsers();
//        model.addAttribute("allUsersList", allUsersList);
//        return "users";
//    }
    //создаем пользователя
//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "new";
//    }

    @PostMapping("/")
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    //обновляем пользователя
    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "/edit";

        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    //Удаляем пользователяч
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    //Показать одного пользователя
//    @GetMapping("/{id}")
//    public String showOneUser(@PathVariable("id") long id1, ModelMap model) {
//        model.addAttribute("user", userService.getUser(id1));
//        return "user";
//    }
    @GetMapping("/{id}")
    public User showOneUser(@PathVariable("id") long id) {
        return userService.getUser(id);
    }
}