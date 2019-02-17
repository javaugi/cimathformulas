/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring.jpapagination;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    //@Query("SELECT e FROM Employee e WHERE e.dept = ?1")
    public Slice<Employee> findByDept(String deptName, Pageable pageable);
}
