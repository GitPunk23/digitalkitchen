package com.digitalkitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.entities.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Integer> {
    
}
