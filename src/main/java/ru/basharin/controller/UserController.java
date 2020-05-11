package ru.basharin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.basharin.model.User;
import ru.basharin.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getAllUsers(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("users", userService.getUsers());
        return "users";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {

        userService.save(user);
        return "redirect:/users";
    }

    @RequestMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @RequestMapping(value = "edit/update", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }
}
