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

    @Column(name = "hits")
    private int hits;

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.tag;
    }

    public void setName(String newName) {
        this.tag = newName;
    }

    public int getHits() {
        return this.hits;
    }

    public void incrementhits() {
        this.hits++;
    }

    public void decrementHits() {
        if (this.hits == 0) {
            return;
        }
        this.hits--;
    }
}
