/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Facade pattern hides the complexities of the system and provides an interface
 * to the client using which the client can access the system. This type of
 * design pattern comes under structural pattern as this pattern adds an
 * interface to existing system to hide its complexities.
 *
 * This pattern involves a single class which provides simplified methods
 * required by client and delegates calls to methods of existing system classes.
 *
 * Provide a unified interface to a set of interfaces in a subsystem. Façade
 * defines a higher-level interface that makes the subsystem easier to use.
 *
 * Where Would I Use This Pattern? As the concept behind facade is to simplify
 * an interface, service oriented architectures make use of the facade pattern.
 * For example, in web services, one web service might provide access to a
 * number of smaller services that have been hidden from the caller by the
 * facade. Similarly, a typical pattern in OSGi bundles is to provide an
 * interface package that is exposed to users of the bundle. All other packages
 * are hidden from the user.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Step 1
Create an interface.
Step 2
Create concrete classes implementing the same interface.
Step 3
Create a facade class.

 */
public class FacadePattern {

    private static final Logger log = LoggerFactory.getLogger(FacadePattern.class);

    public static void main(String[] args) {
        ShapeMakerFacade shapeMaker = new ShapeMakerFacade();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();

        testDatabaseHelper();
    }

    public static void testDatabaseHelper() {
        DatabaseHelperFacade.generateReport(DatabaseHelperFacade.DBTypes.MYSQL, DatabaseHelperFacade.ReportTypes.HTML);
        DatabaseHelperFacade.generateReport(DatabaseHelperFacade.DBTypes.ORACLE, DatabaseHelperFacade.ReportTypes.PDF);
    }

}
