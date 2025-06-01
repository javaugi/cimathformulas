/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.empbilpayroll;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.deptCode = ?1")
    public Slice<Employee> findByDeptCode(String deptCode, Pageable pageable);

    @Query("SELECT e FROM Employee e join Department d on d.code = e.deptCode WHERE d.name = ?1")
    public Slice<Employee> findByDeptName(String deptName, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.lastName = ?1")
    public Slice<Employee> findByLastName(String lastName, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.firstName = ?1")
    public Slice<Employee> findByFirstName(String firstName, Pageable pageable);
}
