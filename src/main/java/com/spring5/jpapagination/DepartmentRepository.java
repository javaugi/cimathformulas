/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.jpapagination;

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
public interface DepartmentRepository extends CrudRepository<Department, Long> {

}
