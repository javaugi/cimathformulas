/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

/**
 * Best Practices for JSON Validation
 * 
 * Use Bean Validation for Simple Rules: For basic field-level validation (required fields, length, patterns)
 * Implement Custom Validators for Complex Logic: When validation requires access to multiple fields or external services
 * Consider JSON Schema for Complex Structures: When you need to validate nested objects or complex relationships
 * Validate Early: Validate input as soon as it enters your system (in controllers)
 * Provide Clear Error Messages: Help API consumers understand what went wrong
 * Consistent Error Responses: Use a standardized format for validation errors
 * Combine Approaches: Use both annotation-based validation and programmatic validation when needed
 * This comprehensive approach ensures your Spring Boot CRUD operations are protected with robust validation at multiple levels.
 * 
 * @author javaugi
 */
public class JsonValidationExample {
    
}
/*
Validating JSON File Content in Spring Boot CRUD Operations
    Validating JSON input is crucial for maintaining data integrity in your Spring Boot CRUD application. Here's a comprehensive guide to implementing JSON validation:

1. Add Validation Dependencies: First, ensure you have the necessary dependencies in your pom.xml:
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
</dependency>

2. Basic Bean Validation Annotate Your Entity Class
@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
}

3. Enable Validation in Controller - PatientValidatorController
4. Custom Validators Create a Custom Validation Annotation
    Create a Custom Validation Annotation
        public @interface AllowedValues {
            String message() default "Value not allowed";
            Class<?>[] groups() default {};
            Class<? extends Payload>[] payload() default {};
            String[] value();
        }
    Implement the Validator
        public class AllowedValuesValidator implements ConstraintValidator<AllowedValues, String> {

            private String[] allowedValues;

            @Override
            public void initialize(AllowedValues constraintAnnotation) {
                this.allowedValues = constraintAnnotation.value();
            }

            @Override
            public boolean isValid(String value, ConstraintValidatorContext context) {
                if (value == null) {
                    return true; // Let @NotNull handle null checks
                }
                return Arrays.asList(allowedValues).contains(value);
            }
        }
    Use the Custom Validator
        public class Appointment implements java.io.Serializable {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            Long id;

            private LocalDateTime appointmentDateTime;
            private String reason;

            @AllowedValues(value = {"SCHEDULED", "COMPLETED", "CANCELLED"}, 
                          message = "Status must be SCHEDULED, COMPLETED, or CANCELLED")
            private String status; // Scheduled, Completed, Cancelled
        }
5. Cross Field Validation 
    Class-Level Validation Annotation : ValidAppointment 
    Class-Level Validator Implementation: ValidAppointmentValidator
    Apply to Entity
        @Entity
        @ValidAppointment
        public class Appointment {
            // ...
        }

6. JSON Schema Validation: For complex validation, consider using JSON Schema:
    Add Dependency
        <dependency>
            <groupId>com.github.everit-org.json-schema</groupId>
            <artifactId>org.everit.json.schema</artifactId>
            <version>1.14.0</version>
        </dependency>
    Create JSON Schema
        src/main/resources/schemas/patient-schema.json:    
            {
              "$schema": "http://json-schema.org/draft-07/schema#",
              "type": "object",
              "required": ["firstName", "lastName", "dateOfBirth"],
              "properties": {
                "firstName": {
                  "type": "string",
                  "minLength": 2,
                  "maxLength": 50
                },
                "lastName": {
                  "type": "string",
                  "minLength": 2,
                  "maxLength": 50
                },
                "dateOfBirth": {
                  "type": "string",
                  "format": "date"
                },
                "phoneNumber": {
                  "type": "string",
                  "pattern": "^\\+?[0-9]{10,15}$"
                }
              }
            }
    Implement Schema Validatio: JsonSchemaValidator 
    Use in Controller
        public class PatientJsonApiController {

            @Autowired
            private JsonSchemaValidator jsonValidator;

            @PostMapping("/validate")
            public ResponseEntity<?> validatePatientJson(@RequestBody String patientJson) {
                try {
                    jsonValidator.validatePatientJson(patientJson);
                    return ResponseEntity.ok().body("JSON is valid");
                } catch (ValidationException e) {
                    return ResponseEntity.badRequest().body(e.getLocalizedMessage());
                }
            }  
        }
7. Global Exception Handling
8. Testing Validation Unit Test Example
*/
