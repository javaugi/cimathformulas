/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.creational;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class SingletonPattern {

    private static final Logger log = LoggerFactory.getLogger(SingletonPattern.class);

    public static void main(String[] args) {
        SingletonPattern pat = new SingletonPattern();
        pat.runExample();
    }

    void runExample() {
        SingleObject object = SingleObject.getInstance();

        //show the message
        object.showMessage();
    }

}
