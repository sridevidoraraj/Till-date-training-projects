package com.gcit.springbootreact.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "lmi")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "lmi_name")
    @JsonProperty("name")
    private String name;

    @Column(name = "system_oid")
    @JsonProperty("systemOid")
    private String systemOid;

    @Column(name = "lmi_validation")
    @JsonProperty("validation")
    private String validation;

    @Column(name = "status")
    @JsonProperty("status")
    private Integer status;

    @Column(name = "created_on")
    @JsonProperty("createdOn")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    @JsonProperty("createdBy")
    private String createdBy;

    @Column(name = "modified_on")
    @JsonProperty("modifiedOn")
    @UpdateTimestamp
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    @JsonProperty("modifiedBy")
    private String modifiedBy;

    public Client() {
    }

//    public Client(String name) {
//        this.name = name;
//    }
//
//    public Client(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    public Client(Long id, String name, String systemOid, String validation, Integer status, LocalDateTime createdOn, String createdBy, LocalDateTime modifiedOn, String modifiedBy) {
        this.id = id;
        this.name = name;
        this.systemOid = systemOid;
        this.validation = validation;
        this.status = status;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemOid() {
        return systemOid;
    }

    public void setSystemOid(String systemOid) {
        this.systemOid = systemOid;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
