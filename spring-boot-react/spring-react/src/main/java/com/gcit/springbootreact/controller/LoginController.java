package com.gcit.springbootreact.controller;

import com.gcit.springbootreact.dto.Response;
import com.gcit.springbootreact.model.Client;
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

    @GetMapping("/{lid}")
    public Login getById(@PathVariable Long lid) {
        return loginRepository.findById(lid).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/")
    public List<Login> getUsers() {
        return loginRepository.findAll();
    }

//    @PostMapping("/")
//    public ResponseEntity addUsers(@RequestBody Login login) throws URISyntaxException {
//        Login savedUsers = loginRepository.save(login);
//        return ResponseEntity.created(new URI("/login/" + savedUsers.getId())).body(savedUsers);
//    }

    @PostMapping("/")
    public Response addUser(@RequestBody Login login){
        Login newUser = loginRepository.save(login);
        return new Response("login successfully");
    }

    @PutMapping("/{lid}")
    public ResponseEntity updateUser(@PathVariable Long lid, @RequestBody Login login) {
        Login currentUser = loginRepository.findById(lid).orElseThrow(RuntimeException::new);
        currentUser.setUserName(login.getUserName());
        currentUser.setPassword(login.getPassword());
        currentUser = loginRepository.save(login);
        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping("/{lid}")
    public ResponseEntity deleteUser(@PathVariable Long lid) {
        loginRepository.deleteById(lid);
        return ResponseEntity.ok().build();
    }
}