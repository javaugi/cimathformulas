/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dao;

import com.spring5.entity.Station;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class StationDaoNativeImpl extends StationDao {
    @Autowired
    private EntityManager entityManager;
    
    public List<String> doQuery(String qString) {
        @SuppressWarnings("unchecked")
        Query query = entityManager.createNativeQuery(qString);
        return query.getResultList();
    }
    
    @Override
    public List<Station> saveAll(List<Station> records) {
        records = super.saveAll(records);                
        return records;
    }
}
