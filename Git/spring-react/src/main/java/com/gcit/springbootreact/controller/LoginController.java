package com.gcit.springbootreact.controller;

import com.gcit.springbootreact.model.Login;
import com.gcit.springbootreact.repository.LoginRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @GetMapping("/{id}")
    public Login getById(@PathVariable Long id) {
        return loginRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/")
    public List<Login> getUsers() {
        return loginRepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity addUsers(@RequestBody Login login) throws URISyntaxException {
        Login savedUsers = loginRepository.save(login);
        return ResponseEntity.created(new URI("/login" + savedUsers.getId())).body(savedUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody Login login) {
        Login currentUser = loginRepository.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setUserName(login.getUserName());
        currentUser.setPassword(login.getPassword());
        currentUser = loginRepository.save(currentUser);
        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        loginRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}