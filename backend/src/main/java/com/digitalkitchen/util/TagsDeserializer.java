package com.digitalkitchen.util;

import java.io.IOException;

import com.digitalkitchen.model.entities.Tags;
import com.digitalkitchen.service.TagsService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class TagsDeserializer extends JsonDeserializer<Tags> {
    @Autowired
    private TagsService tagsService;

    @Override
    public Tags deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String tag = p.getValueAsString();
        return tagsService.getTagByName(tag).orElse(null);
    }
}

