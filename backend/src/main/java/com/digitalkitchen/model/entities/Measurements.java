package com.digitalkitchen.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;


@Entity
@Data
@Table(name = "measurements")
public class Measurements {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "measurements")
    private String measurement;
}