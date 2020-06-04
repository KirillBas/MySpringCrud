package ru.basharin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.basharin.model.User;
import ru.basharin.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin/users", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();

        if (users.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(users);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        model.addAttribute("current_user", userService.getUserById(id));
        return "user";
    }

    @GetMapping(value = "/user/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping(value = "/admin/users")
    public ResponseEntity<Void> addUser(User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/delete/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    public void deleteUser(@PathVariable long id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public String getUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    // problem update delete relationship
    @PostMapping(value = "/admin/edit/update")
    public ResponseEntity<Void> editUser(User user) {
        User currentUser = userService.getUserById(user.getId());
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        userService.update(currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String loginPage() {
//        return "login";
//    }

    @RequestMapping(value = "/admin/users")
    public String index() {
        return "users";
    }

//    @RequestMapping(value = "/user")
//    public String userProfile() {
//        return "users";
//    }
}
