package com.gcit.springbootreact.ServiceTest;


import com.gcit.springbootreact.Service.ClientConfigService;
import com.gcit.springbootreact.Service.ClientService;
import com.gcit.springbootreact.Utils.TestUtils;
import com.gcit.springbootreact.model.Client;
import com.gcit.springbootreact.model.ClientConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.NotNull;
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
public class ClientConfigServiceTest {

    @Autowired
    private ClientConfigService clientConfigService;

    @Autowired
    private ClientService clientService;

    @Test
    @Transactional
    public void saveClientConfigTest() {
        clientConfigService.deleteClientConfig();
        ClientConfig clientConfig = TestUtils.getClientConfig(1L, "sam");
        ClientConfig actual = clientConfigService.saveClientConfig(clientConfig);
        assertEquals(actual.getKey(), "sam");
    }

    @Test
    @Transactional
    public void createClientConfigTest() {
        clientService.deleteClient();
        Client client = TestUtils.getClient(2L, "ram");
        Client actual = clientService.saveClient(client);
        clientConfigService.deleteClientConfig();
        ClientConfig clientConfig = TestUtils.getClientConfig(2L, "shyam");
        clientConfig.setClient(actual);
        ClientConfig actual1 = clientConfigService.createClientConfig(clientConfig);
        assertEquals(actual1.getClient().getName(), "ram");
        assertEquals(actual1.getKey(), "shyam");
    }

    @Test
    @Transactional
    public void findByIdTest() {
        clientConfigService.deleteClientConfig();
        ClientConfig clientConfig = TestUtils.getClientConfig(2L, "ram");
        ClientConfig actual = clientConfigService.saveClientConfig(clientConfig);
        Optional<ClientConfig> savedClientConfig = clientConfigService.findById(actual.getId());
        assertEquals(savedClientConfig.get().getKey(), actual.getKey());
    }

    @Test
    @Transactional
    public void getClientConfigsTest() {

        clientConfigService.deleteClientConfig();
        List<ClientConfig> clientConfigList = TestUtils.getClientConfigs();
        List<ClientConfig> clientConfigs = clientConfigService.saveClientConfigs(clientConfigList);
        List<ClientConfig> actual = clientConfigService.getClientConfigs();
        assertEquals(actual.size(), clientConfigs.size());

    }

    @Test
    @Transactional
    public void updateClientConfigTest() {
        clientConfigService.deleteClientConfig();
        ClientConfig clientConfig = TestUtils.getClientConfig(3L, "ravi");
        ClientConfig actual = clientConfigService.saveClientConfig(clientConfig);
        String actualKey = actual.getKey();
        assertEquals(actualKey, "ravi");
        ClientConfig actual1 = clientConfigService.updateClientConfig(actual.getId(), clientConfig);
        ClientConfig clientConfig1 = TestUtils.getClientConfig(4L, "joe");
        ClientConfig actualClientConfig = clientConfigService.saveClientConfig(clientConfig1);
        String actualClientConfigKey = actualClientConfig.getKey();
        assertEquals(actualClientConfigKey, "joe");
    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        clientConfigService.deleteClientConfig();
        ClientConfig clientConfig = TestUtils.getClientConfig(1l, "david");
        ClientConfig actual = clientConfigService.saveClientConfig(clientConfig);
        String actualKey = actual.getKey();
        assertEquals(actualKey, "david");
        ClientConfig delete = clientConfigService.deleteById(actual.getId());
        assertNull(delete);
    }

}
