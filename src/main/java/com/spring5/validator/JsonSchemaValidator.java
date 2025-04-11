/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.validator;

import jakarta.validation.ValidationException;
import java.io.IOException;
import java.io.InputStream;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;

@Service
public class JsonSchemaValidator {
    
    private final Schema patientSchema;
    
    public JsonSchemaValidator() throws IOException {
        InputStream schemaStream = getClass().getResourceAsStream("/schemas/patient-schema.json");
        JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
        this.patientSchema = SchemaLoader.load(rawSchema);
    }
    
    public void validatePatientJson(String json) throws ValidationException {
        JSONObject patientJson = new JSONObject(json);
        patientSchema.validate(patientJson);
    }
}
