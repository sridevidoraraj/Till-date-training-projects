package com.gcit.springbootreact.ServiceTest;


import com.gcit.springbootreact.Service.LoginService;
import com.gcit.springbootreact.Utils.TestUtils;
import com.gcit.springbootreact.model.Client;
import com.gcit.springbootreact.model.ClientConfig;
import com.gcit.springbootreact.model.Login;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    @Transactional
    public void saveUserTest() {
        loginService.deleteUsers();
        Login login = TestUtils.getUser(1l, "shiva");
        Login actual = loginService.saveUser(login);
        assertEquals(actual.getUserName(), "shiva");
    }

    @Test
    @Transactional
    public void findByIdTest() {
        loginService.deleteUsers();
        Login login = TestUtils.getUser(2L, "ram");
        Login actual = loginService.saveUser(login);
        Optional<Login> savedUser = loginService.findById(actual.getId());
        assertEquals(savedUser.get().getUserName(), actual.getUserName());
    }

    @Test
    @Transactional
    public void getUsersTest() {

        loginService.deleteUsers();
        List<Login> loginList = TestUtils.getUsers();
        List<Login> user = loginService.saveUsers(loginList);
        List<Login> actual = loginService.getUsers();
        assertEquals(actual.size(), user.size());
    }

    @Test
    @Transactional
    public void updateUserTest() {
        loginService.deleteUsers();
        Login login = TestUtils.getUser(3L, "ravi");
        Login actual = loginService.saveUser(login);
        String actualUserName = actual.getUserName();
        assertEquals(actualUserName, "ravi");
        Login actual1 = loginService.updateUser(actual.getId(), login);
        Login user1 = TestUtils.getUser(4L, "joe");
        Login actualUser = loginService.saveUser(user1);
        String actualLoginUser = actualUser.getUserName();
        assertEquals(actualLoginUser, "joe");
    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        loginService.deleteUsers();
        Login login = TestUtils.getUser(1l, "david");
        Login actual = loginService.saveUser(login);
        String actualUserName = actual.getUserName();
        assertEquals(actualUserName, "david");
        Login delete = loginService.deleteById(actual.getId());
        assertNull(delete);
    }

}
