/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring.jpapagination;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Component
public class EmployeeClient {

    @Autowired
    private EmployeeRepository repo;

    public void run() {
        List<Employee> employees = createEmployees();
        repo.saveAll(employees);

        System.out.println(" ***** finding all employees --");
        Iterable<Employee> all = repo.findAll();
        all.forEach(System.out::println);

        System.out.println(" -- paginating where dept is Sales --");
        Slice<Employee> slice = null;
        Pageable pageable = PageRequest.of(0, 3, Sort.by("salary"));
        while (true) {
            slice = repo.findByDept("Sales", pageable);
            int number = slice.getNumber();
            int numberOfElements = slice.getNumberOfElements();
            int size = slice.getSize();
            System.out.printf("slice info - page number %s, numberOfElements: %s, size: %s%n",
                    number, numberOfElements, size);
            List<Employee> employeeList = slice.getContent();
            employeeList.forEach(System.out::println);
            if (!slice.hasNext()) {
                break;
            }
            pageable = slice.nextPageable();
        }
    }

    private List<Employee> createEmployees() {
        return Arrays.asList(
                Employee.create("Diana", "Sales", 2000),
                Employee.create("Mike", "Sales", 1000),
                Employee.create("Rose", "IT", 4000),
                Employee.create("Sara", "Sales", 3000),
                Employee.create("Andy", "Sales", 3000),
                Employee.create("Charlie", "Sales", 2500),
                Employee.create("Jim", "Sales", 4500),
                Employee.create("Sam", "Sales", 2500),
                Employee.create("Adam", "Sales", 5000),
                Employee.create("Jane", "Sales", 5500),
                Employee.create("Joe", "Sales", 1500)
        );
    }
}
