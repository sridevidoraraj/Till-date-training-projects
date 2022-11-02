package com.javatechie.crud.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_TBL")
public class Product {

    @Id
    @SequenceGenerator(name = "hibernate_seq1", sequenceName = "Product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq1")
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String location;
}
