package com.gcit.springbootreact.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gcit.springbootreact.model.Client;
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
public class ClientControllerTest {

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
    public void createClientTest() throws Exception {

        Client client = new Client(1L, "david", "d", "123", 1, null, "1234", null, "1234");


        String JsonRequest = om.writeValueAsString(client);

        MvcResult result = mockMvc.perform(post("/clients/addClient").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()).andReturn();

        String resultContext = result.getResponse().getContentAsString();

        Client responseClient = om.readValue(resultContext, Client.class);

        Assert.assertEquals(responseClient.getName(), client.getName());
    }

    @Test
    public void getClientTest() throws Exception {

        MvcResult result = mockMvc
                .perform(get("/clients/39").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        Client response = om.readValue(resultContent, Client.class);

        Assert.assertTrue(response.getId() == 39);

    }

    @Test
    public void updateClientTest() throws Exception {

        Client client = new Client(34L, "sam", "s", "111", 3, null, "222", null, "333");

        String JsonRequest = om.writeValueAsString(client);

        MvcResult result = mockMvc.perform(put("/clients/").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContext = result.getResponse().getContentAsString();

        Client responseClient = om.readValue(resultContext, Client.class);

        Assert.assertEquals(responseClient.getName(), client.getName());

    }

    @Test
    public void deleteClientTest() throws Exception {

        Client client = new Client(1L, "david", "d", "123", 1, null, "1234", null, "1234");


        String JsonRequest = om.writeValueAsString(client);

        MvcResult mvcResult = mockMvc.perform(post("/clients/addClient").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()).andReturn();

        String resultContext = mvcResult.getResponse().getContentAsString();

        Client responseClient = om.readValue(resultContext, Client.class);

        String clientId = String.valueOf(responseClient.getId());

        MvcResult result = mockMvc
                .perform(delete("/clients/" + clientId).content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());

    }

}
