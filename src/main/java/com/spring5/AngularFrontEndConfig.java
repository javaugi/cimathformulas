/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

/**
 *
 * @author javaugi
 */
public class AngularFrontEndConfig {
    
}
//npm install -g @angular/cli@19.2.9  (npm uninstall -g @angular/cli@19.2.9 to remove)
//ng new angularclient after cd to project/src/main/webapp

/*
For a Spring Boot and Angular project structured as a single Maven project, the angularclient directory typically resides within 
    the src/main directory. A common approach is to place it under src/main/frontend or src/main/webapp, keeping client-side code 
\   separate from the Java backend. The pom.xml file should be configured to build the Angular application and include the compiled 
    output in the Spring Boot application's static resources, usually under src/main/resources/public.
Code

├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── app
│   │   │               ├── ... (Spring Boot application classes)
│   │   │               └── Application.java
│   │   ├── frontend (or webapp)
│   │   │   └── angularclient
│   │   │       ├── src
│   │   │       │   └── app
│   │   │       │       ├── ... (Angular components, services, etc.)
│   │   │       │       └── app.module.ts
│   │   │       ├── angular.json
│   │   │       ├── package.json
│   │   │       └── tsconfig.json
│   │   ├── resources
│   │   │   ├── static
│   │   │   │   └── ... (Compiled Angular output)
│   │   │   ├── templates
│   │   │   └── application.properties
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── app
│                       └── ... (Spring Boot tests)
├── pom.xml
*/