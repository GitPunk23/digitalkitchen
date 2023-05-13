package com.digitalkitchen.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;


@Entity
@Table(name = "measurements")
public class Measurements {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "measurement")
    private String measurement;

    public int getID() {
        return this.ID;
    }

    public String getMeasurement() {
        return this.measurement;
    }
}