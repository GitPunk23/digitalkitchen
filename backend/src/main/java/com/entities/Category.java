package com.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import javax.persistence.Column;


@Entity
@Table(name = "categories")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "categories")
    private List<Recipes> recipes;

    /**
     * Constructor for insertion
     * @param name
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Constructor for retrieval
     * @param ID
     * @param name
     */
    public Category(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return this.ID;
    }

    public String getValues() {
        String sql = "('"+ this.name + "')";
        return sql;
    }

}