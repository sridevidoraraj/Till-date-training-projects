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
    @Query(nativeQuery = true, value ="SELECT lmi_config.id,lmi_config.key_params, lmi_config.value_params,lmi_config.fk_client_id FROM lmi INNER JOIN lmi_config ON lmi.id=lmi_config.fk_client_id WHERE lmi_config.fk_client_id = :client")
    List<ClientConfig> findAllByClientId(@PathParam("client") Long client);
}
