package com.gcit.springbootreact.dto;

import com.gcit.springbootreact.model.Client;

public class ClientConfigDto {
    Long id;
    String key;
    String value;
    Client client;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
