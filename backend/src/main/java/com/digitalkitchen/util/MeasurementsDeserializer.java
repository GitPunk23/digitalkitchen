package com.digitalkitchen.util;

import java.io.IOException;

import com.digitalkitchen.model.entities.Measurements;
import com.digitalkitchen.service.MeasurementsService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class MeasurementsDeserializer extends JsonDeserializer<Measurements> {
    @Autowired
    private MeasurementsService measurementsService;

    @Override
    public Measurements deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int measurementsId = Integer.parseInt(p.getValueAsString());
        return measurementsService.getMeasurementById(measurementsId).orElse(null);
    }
}

