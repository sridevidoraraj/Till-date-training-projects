package com.gcit.springbootreact.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "client_config")
public class ClientConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "key_params")
    @JsonProperty("key")
    private String key;

    @Column(name = "value_params")
    @JsonProperty("value")
    private String value;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_client_name", referencedColumnName = "lmi_name")
//    @JsonIgnore
    private Client client;


    public ClientConfig(){}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getvalue(){
        return value;
    }

    public void setvalue(String value){
        this.value = value;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}
