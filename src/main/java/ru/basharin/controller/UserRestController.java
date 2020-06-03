package ru.basharin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.basharin.model.User;
import ru.basharin.service.UserService;

import java.util.List;

@RestController("/rest")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/rest/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(value = "/rest/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping(value = "/rest/admin/users")
    @ResponseBody
    public ResponseEntity<Void> addUser(User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/rest/admin/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/rest/admin/edit/update")
    @ResponseBody
    public ResponseEntity<Void> editUser(User user) {
        User currentUser = userService.getUserById(user.getId());
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        userService.update(currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/rest/login")
    public ResponseEntity<User> getAuth(@ModelAttribute String name, @ModelAttribute String password) {
        return ResponseEntity.ok(userService.authUser(name, password));
    }
}
