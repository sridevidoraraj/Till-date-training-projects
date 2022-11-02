package com.gcit.springbootreact.controller;

import com.gcit.springbootreact.Service.LoginService;
import com.gcit.springbootreact.model.Login;
import com.gcit.springbootreact.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/{id}")
    public Login getById(@PathVariable Long id) {
        return loginService.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/")
    public List<Login> getUsers() {
        return loginService.getUsers();
    }

    @PostMapping("/")
    public ResponseEntity addUsers(@RequestBody Login login) throws URISyntaxException {
        Login savedUsers = loginService.saveUser(login);
        return ResponseEntity.created(new URI("/login/" + savedUsers.getId())).body(savedUsers);
    }

    @PutMapping("/{id}")
    public Login updateUser(@PathVariable Long id, @RequestBody Login login) {

        return loginService.updateUser(id, login);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        loginService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteUsers() {
        loginService.deleteUsers();
        return ResponseEntity.ok().build();
    }
}