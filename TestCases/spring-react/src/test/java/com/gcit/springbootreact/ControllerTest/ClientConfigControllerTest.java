package com.gcit.springbootreact.ControllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gcit.springbootreact.Service.ClientService;
import com.gcit.springbootreact.model.Client;
import com.gcit.springbootreact.model.ClientConfig;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientConfigControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ClientService clientService;
    @Autowired
    private WebApplicationContext context;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        om.registerModule(new JavaTimeModule());
    }


    @Test
    public void createClientConfigTest() throws Exception {

        Client client = new Client(1L, "david", "d", "111", 1, null, "222", null, "333");

        clientService.saveClient(client);

        ClientConfig clientConfig = new ClientConfig(3L, "john", "j", client);


        String JsonRequest = om.writeValueAsString(clientConfig);

        MvcResult result = mockMvc.perform(post("/config").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContext = result.getResponse().getContentAsString();

        ClientConfig responseClientConfig = om.readValue(resultContext, ClientConfig.class);

        Assert.assertEquals(responseClientConfig.getKey(), clientConfig.getKey());
    }

    @Test
    public void getClientConfigTest() throws Exception {

        MvcResult result = mockMvc
                .perform(get("/config/3").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        ClientConfig responseClientConfig = om.readValue(resultContent, ClientConfig.class);

        Assert.assertTrue(responseClientConfig.getId() == 3);


    }

    @Test
    public void updateClientConfigTest() throws Exception {

        ClientConfig clientConfig = new ClientConfig(1L, "ram", "r", null);

        String JsonRequest = om.writeValueAsString(clientConfig);

        MvcResult result = mockMvc.perform(put("/config/3").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContext = result.getResponse().getContentAsString();

        ClientConfig responseClientConfig = om.readValue(resultContext, ClientConfig.class);

        Assert.assertEquals(responseClientConfig.getKey(), clientConfig.getKey());

    }

    @Test
    public void deleteClientConfigTest() throws Exception {

        Client client = new Client(2L, "david", "d", "111", 1, null, "222", null, "333");

        clientService.saveClient(client);

        ClientConfig clientConfig = new ClientConfig(4L, "john", "j", client);


        String JsonRequest = om.writeValueAsString(clientConfig);

        MvcResult result = mockMvc.perform(post("/config").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContext = result.getResponse().getContentAsString();

        ClientConfig responseClientConfig = om.readValue(resultContext, ClientConfig.class);

        String clientConfigId = String.valueOf(responseClientConfig.getId());

        MvcResult resultClientConfig = mockMvc
                .perform(delete("/config/" + clientConfigId).content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        Assert.assertEquals(200, resultClientConfig.getResponse().getStatus());


    }

}
