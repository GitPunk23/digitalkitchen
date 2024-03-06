package com.digitalkitchen.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;


@Entity
@Data
@NoArgsConstructor
@Table(name = "tags")
public class Tags {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "tag")
    private String tag;

    @Column(name = "hits")
    private int hits;

    public Tags(String tag) {
        this.tag = tag;
    }

    public void incrementhits() {
        this.hits++;
    }

    public void decrementHits() {
        if (this.hits != 0) {
            this.hits--;
        }
    }
}
