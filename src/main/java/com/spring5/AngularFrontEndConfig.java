/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

//https://www.baeldung.com/spring-boot-angular-web
public class AngularFrontEndConfig {
    //download and intall node.js   
    //node -v
    //npm -v
    //npm install -g @angular/cli
    //update
    //npm install -g @angular/cli@latest
    //ng update @angular/core @angular/cli
    //ng new angularclient in the dir /e/springtutorial/cimathformulas/src/main/webapp
    
}
//npm install -g @angular/cli@19.2.9  (npm uninstall -g @angular/cli@19.2.9 to remove)
//ng new angularclient after cd to project/src/main/webapp

/*
Installing Node.js and Angular (2+):Here's a step-by-step guide to install Node.js and set up an Angular 2+ development environment:

1. Install Node.js and npm
For Windows/macOS:
    Download Node.js from the official website https://nodejs.org/
    For most users, download the LTS (Long Term Support) version
    The installer includes npm (Node Package Manager)
    Run the installer and follow the installation wizard
    Verify installation by opening a terminal/command prompt and running:

    bash
    node -v
    npm -v
    This should display the installed versions.
For Linux (Ubuntu/Debian):
    bash
    curl -sL https://deb.nodesource.com/setup_16.x | sudo -E bash -
    sudo apt-get install -y nodejs
2. Install Angular CLI
    The Angular CLI (Command Line Interface) is the official tool for Angular development:
        bash
        npm install -g @angular/cli
        Verify installation:

        bash
        ng version
3. Create a New Angular Project
    bash
    ng new my-angular-app
    cd my-angular-app

The CLI will ask you some configuration questions:
    Would you like to add Angular routing? (Yes/No)
    Which stylesheet format would you like to use? (CSS/SCSS/Less/Stylus)

4. Run the Development Server
    bash
    ng serve
    This will:
        Compile your Angular application
        Start a development server at http://localhost:4200
        Enable live reload (automatically refreshes when you make changes)
5. Recommended Additional Tools
    Visual Studio Code (Free IDE with excellent Angular support)
    Install the Angular Essentials extension pack
    Browser Developer Tools
    Chrome or Edge with Angular DevTools extension
6. Updating Angular CLI and Angular
To update to the latest versions:
    bash
    npm install -g @angular/cli@latest
    ng update @angular/core @angular/cli

    Version Information
        Angular 2+ refers to all versions from Angular 2 onward
        Current stable version is Angular 16+ (as of 2023)
        All versions after Angular 2 follow semantic versioning

Troubleshooting
    If you encounter permission errors on macOS/Linux:

    bash
    sudo chown -R $USER /usr/local/lib/node_modules
    If you get command not found errors, ensure Node.js is in your PATH.
*/


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