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
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/rest/admin/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/rest/admin/edit/update")
    public ResponseEntity<Void> editUser(@RequestBody User user) {
        User currentUser = userService.getUserById(user.getId());
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        userService.update(currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<User> getAuth(@ModelAttribute String name, @ModelAttribute String password) {
        return ResponseEntity.ok(userService.authUser(name, password));
    }

    @GetMapping(value = "/user/name={name}")
    public ResponseEntity<User> getByUserName(@PathVariable("name") String name) {
        User user = userService.getUserByName(name);
        return ResponseEntity.ok(user);
    }
}
