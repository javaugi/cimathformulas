/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Office extends AbstractProperty {

    private static final Logger log = LoggerFactory.getLogger(Apartment.class);

    private Office(String builder, String address, float price) {
        this.builder = builder;
        this.address = address;
        this.price = price;
        this.type = PropertyType.Office;
    }

    public static Property makePriperty() {
        return new Office("Town & Country", "1000 Main", 50000);
    }

    @Override
    public void add(Property property) {
        properties.add(property);
    }

    @Override
    public void remove(Property property) {
        properties.remove(property);
    }

    @Override
    public void purchase() {
        // TODO Auto-generated method stub
    }

    @Override
    public void sell() {
        // TODO Auto-generated method stub
    }

    @Override
    public void print() {
        for (Property prop : properties) {
            prop.print();
        }
    }
}
