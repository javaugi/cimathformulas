/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Flyweight pattern is primarily used to reduce the number of objects created
 * and to decrease memory footprint and increase performance. This type of
 * design pattern comes under structural pattern as this pattern provides ways
 * to decrease object count thus improving the object structure of application.
 *
 * Flyweight pattern tries to reuse already existing similar kind objects by
 * storing them and creates new object when no matching object is found. We will
 * demonstrate this pattern by drawing 20 circles of different locations but we
 * will create only 5 objects. Only 5 colors are available so color property is
 * used to check already existing Circle objects.
 *
 * Simply put, the flyweight pattern is based on a factory which recycles
 * created objects by storing them after creation. Each time an object is
 * requested, the factory looks up the object in order to check if it’s already
 * been created. If it has, the existing object is returned – otherwise, a new
 * one is created, stored and then returned.
 *
 * The flyweight object’s state is made up of an invariant component shared with
 * other similar objects (intrinsic) and a variant component which can be
 * manipulated by the client code (extrinsic).
 *
 * It’s very important that the flyweight objects are immutable: any operation
 * on the state must be performed by the factory.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Synchronized Map is usually used
 */
public class FlyweightPattern {

    private static final Logger log = LoggerFactory.getLogger(FlyweightPattern.class);
    private static final String colors[] = {"Red", "Green", "Blue", "White", "Black"};

    public static void main(String[] args) {

        for (int i = 0; i < 20; ++i) {
            Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }

    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private static int getRandomX() {
        return (int) (Math.random() * 100);
    }

    private static int getRandomY() {
        return (int) (Math.random() * 100);
    }

}
