/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


//@Repository
@Transactional
public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Customer findByFirstName(@Param("firstname") String firstName);
	public List<Customer> findByLastName(@Param("lastName") String lastName);

}
