package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.models.User;

import java.security.Principal;


@RestController
@RequestMapping("/user")
public class UserController {
	private final UserServiceImpl userServiceImp;

	@Autowired
	public UserController(UserServiceImpl userServiceImp) {
		this.userServiceImp = userServiceImp;
	}

	@GetMapping()
	public String showThisUser(ModelMap model, Principal principal) {
		model.addAttribute("user", userServiceImp.findByUsername(principal.getName()));
		return "user";
	}
//@GetMapping()
//public User getUser(@PathVariable long id) {
//	User user = userServiceImp.getUser(id);
//	return user;
//}

}