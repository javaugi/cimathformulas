/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring.jpapagination;

import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class EmployeeMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("Contains employee  " + context.containsBeanDefinition("employee"));
        System.out.println("Contains EmployeeClient  " + context.containsBeanDefinition("employeeClient"));

        EmployeeClient exampleClient = context.getBean(EmployeeClient.class);
        exampleClient.run();
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        emf.close();

    }
}
