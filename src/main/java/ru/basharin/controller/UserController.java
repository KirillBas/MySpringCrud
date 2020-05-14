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
import ru.basharin.service.RoleService;
import ru.basharin.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getAllUsers(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("users", userService.getUsers());
        return "users";
    }
    // TODO: 12.05.2020 придумать как выдернуть роль из сета

//    @RequestMapping(value = "users", method = RequestMethod.GET)
//    public String getRolesByUser(@ModelAttribute("user") User user, ModelMap model) {
//        model.addAttribute("roles", roleService.getRolesByUserId(user));
//        return "users";
//    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    // problem update delete relationship
    @RequestMapping(value = "/admin/edit/update", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") User user) {
        User currentUser = userService.getUserById(user.getId());
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        userService.update(currentUser);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(ModelMap modelMap) {
        return "login";
    }
}
