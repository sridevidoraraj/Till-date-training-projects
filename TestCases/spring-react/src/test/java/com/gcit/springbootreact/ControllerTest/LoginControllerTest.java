package com.gcit.springbootreact.ControllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gcit.springbootreact.model.Login;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        om.registerModule(new JavaTimeModule());
    }

    @Test
    public void createUserTest() throws Exception {

        Login login = new Login(1L, "shiva", "12345678");

        String JsonRequest = om.writeValueAsString(login);

        MvcResult result = mockMvc.perform(post("/login/").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()).andReturn();

        String resultContext = result.getResponse().getContentAsString();

        Login responseUser = om.readValue(resultContext, Login.class);

        Assert.assertEquals(responseUser.getUserName(), login.getUserName());
    }

    @Test
    public void getUserTest() throws Exception {

        MvcResult result = mockMvc
                .perform(get("/login/1").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        Login responseUser = om.readValue(resultContent, Login.class);

        Assert.assertTrue(responseUser.getId() == 1);

    }

    @Test
    public void updateUserTest() throws Exception {

        Login login = new Login(4L, "john", "67890");

        String JsonRequest = om.writeValueAsString(login);

        MvcResult result = mockMvc.perform(put("/login/1").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContext = result.getResponse().getContentAsString();

        Login responseUser = om.readValue(resultContext, Login.class);

        Assert.assertEquals(responseUser.getUserName(), login.getUserName());

    }

    @Test
    public void deleteUserTest() throws Exception {

        Login login = new Login(5L, "sriram", "13579");


        String JsonRequest = om.writeValueAsString(login);

        MvcResult mvcResult = mockMvc.perform(post("/login/").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()).andReturn();

        String resultContext = mvcResult.getResponse().getContentAsString();

        Login responseUser = om.readValue(resultContext, Login.class);

        String userId = String.valueOf(responseUser.getId());

        MvcResult resultUser = mockMvc
                .perform(delete("/login/" + userId).content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        Assert.assertEquals(200, resultUser.getResponse().getStatus());

    }
}
