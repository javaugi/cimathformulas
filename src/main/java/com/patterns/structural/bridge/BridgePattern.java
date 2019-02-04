/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bridge is used when we need to decouple an abstraction from its
 * implementation so that the two can vary independently. This type of design
 * pattern comes under structural pattern as this pattern decouples
 * implementation class and abstract class by providing a bridge structure
 * between them.
 *
 * This pattern involves an interface which acts as a bridge which makes the
 * functionality of concrete classes independent from interface implementer
 * classes. Both types of classes can be altered structurally without affecting
 * each other.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Step 1
Create bridge implementer interface.
Step 2
Create concrete bridge implementer classes implementing the DrawAPI interface.
Step 3
Create an abstract class Shape using the DrawAPI interface.
Step 4
Create concrete class implementing the Shape interface.
 */
public class BridgePattern {

    private static final Logger log = LoggerFactory.getLogger(BridgePattern.class);

    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
