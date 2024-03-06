package com.digitalkitchen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.model.entities.Tags;

import static com.digitalkitchen.util.QueryConstants.GET_ALL_TAGS;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Integer> {
    
    public Optional<Tags> findByTag(String tag);

    @Query(GET_ALL_TAGS)
    public List<String> getAllTags();
}
