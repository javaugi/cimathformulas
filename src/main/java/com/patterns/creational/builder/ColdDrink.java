/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.creational.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public abstract class ColdDrink implements Item {

    private static final Logger log = LoggerFactory.getLogger(ColdDrink.class);

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
