package com.digitalkitchen.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;


@Entity
@Table(name = "tags")
public class Tags {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "tag")
    private String tag;

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.tag;
    }

    public void setName(String newName) {
        this.tag = newName;
    }
}
