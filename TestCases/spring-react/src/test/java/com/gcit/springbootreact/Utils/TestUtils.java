package com.gcit.springbootreact.Utils;


import com.gcit.springbootreact.model.Client;
import com.gcit.springbootreact.model.ClientConfig;
import com.gcit.springbootreact.model.Login;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
        public static Client getClient(Long id ,String name){
            return new Client(id , name , "d", "123" , 1 , null , "1234" , null, "1234");
        }

        public static List<Client> getClients(){
           List<Client> clientList = new ArrayList<>();
           Client client1 = TestUtils.getClient(1L, "david");
           Client client2 = TestUtils.getClient(2L, "james");
           clientList.add(client1);
           clientList.add(client2);
           return clientList;
        }

        public static ClientConfig getClientConfig(Long id, String key){
            return new ClientConfig(id, key , "1234" , null);
        }

        public static List<ClientConfig> getClientConfigs(){
            List<ClientConfig> clientConfigList = new ArrayList<>();
            ClientConfig clientConfig1 = TestUtils.getClientConfig(1l, "sam");
            ClientConfig clientConfig2 = TestUtils.getClientConfig(2l, "ram");
            clientConfigList.add(clientConfig1);
            clientConfigList.add(clientConfig2);
            return clientConfigList;
        }

        public static Login getUser(Long id, String userName){
            return new Login(id, userName, "123456");
        }

        public static List<Login> getUsers(){
            List<Login> loginList = new ArrayList<>();
            Login login1 = TestUtils.getUser(1L, "shiva");
            Login login2 = TestUtils.getUser(2L, "keerthana");
            loginList.add(login1);
            loginList.add(login2);
            return loginList;
        }
}
