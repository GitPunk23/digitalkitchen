package com.entities;

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

    /**
     * Constructor for insertion
     * @param measurement
     */
    public Measurements(String measurement) {
        this.measurement = measurement;
    }

    /**
     * Constructor for retrieval
     * @param ID
     * @param measurement
     */
    public Measurements(int ID, String measurement) {
        this.ID = ID;
        this.measurement = measurement;
    }

    public int getID() {
        return this.ID;
    }

    public String getValues() {
        String sql = "('"+ this.measurement +"')";
        return sql;
    }

}