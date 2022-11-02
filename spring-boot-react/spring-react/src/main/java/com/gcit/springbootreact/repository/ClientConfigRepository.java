package com.gcit.springbootreact.repository;

import com.gcit.springbootreact.dto.ClientConfigDto;
import com.gcit.springbootreact.model.ClientConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface ClientConfigRepository extends JpaRepository<ClientConfig, Long> {
    @Query(nativeQuery = true, value ="SELECT client_config.id,client_config.key_params, client_config.value_params,client_config.fk_client_id FROM lmi INNER JOIN client_config ON lmi.id=client_config.fk_client_id WHERE client_config.fk_client_id = :client")
    List<ClientConfig> findAllByClientId(@PathParam("client") Long client);


}
