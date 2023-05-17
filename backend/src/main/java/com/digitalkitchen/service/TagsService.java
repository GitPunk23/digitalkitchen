package com.digitalkitchen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.Tags;
import com.digitalkitchen.repository.TagsRepository;

@Service
public class TagsService {

    @Autowired
    private TagsRepository repository;
    
    public List<Tags> getAllTags() {
        return repository.findAll();
    }
    
    public Optional<Tags> getTagById(int id) {
        return repository.findById(id);
    }

    public Optional<Tags> getTagByName(String name) {
        return repository.findByTag(name);
    }

    public Tags addTag(Tags tag) {
        return repository.save(tag);
    }

    public void updateTag(Tags recipe) {
        repository.save(recipe);
    }

    public void deleteTagById(int id) {
        repository.deleteById(id);
    }

}
