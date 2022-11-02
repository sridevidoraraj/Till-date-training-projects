package com.gcit.springbootreact.Service;

import com.gcit.springbootreact.model.Login;
import com.gcit.springbootreact.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public Login saveUser(Login login) {
        Login log = loginRepository.save(login);
        return log;
    }

    public Login updateUser(Long id, Login login) {
        Login currentUser = loginRepository.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setUserName(login.getUserName());
        currentUser.setPassword(login.getPassword());
        currentUser = loginRepository.save(login);
        return currentUser;
    }

    public Optional<Login> findById(Long id) {
        return loginRepository.findById(id);
    }

    public List<Login> getUsers() {
        List<Login> users = loginRepository.findAll();
        return users;
    }

    public List<Login> saveUsers(List<Login> users) {
        return loginRepository.saveAll(users);
    }

    public void deleteUsers() {
        loginRepository.deleteAll();
    }

    public Login deleteById(Long id) {
        loginRepository.deleteById(id);
        return null;
    }
}
