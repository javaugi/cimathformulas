/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.facade;

import com.patterns.creational.abstractfactory.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Square implements Shape {

    private static final Logger log = LoggerFactory.getLogger(Square.class);

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
