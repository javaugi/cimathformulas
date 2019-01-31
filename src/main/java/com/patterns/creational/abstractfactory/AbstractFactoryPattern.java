/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.creational.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://www.tutorialspoint.com/design_pattern/design_pattern_overview.htm
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class AbstractFactoryPattern {

    private static final Logger log = LoggerFactory.getLogger(AbstractFactoryPattern.class);

    public static void main(String[] args) {
        // Step 1 Create an interface.
        // Step 2 Create concrete classes implementing the same interface.
        // Step 3 Create an Abstract class to get factories for Normal and Rounded Shape Objects.
        // Step 4 Create Factory classes extending AbstractFactory to generate object of concrete class based on given information.

        // Step 5 Create a Factory generator/producer class to get factories by passing an information such as Shape
        // Step 6 Use the FactoryProducer to get AbstractFactory in order to get factories of concrete classes by
        //  passing an information such as type.
        AbstractFactoryPattern fact = new AbstractFactoryPattern();
        fact.runExampe();
    }

    void runExampe() {
//get rounded shape factory
        AbstractFactory shapeFactory = FactoryProducer.getFactory(false);
        //get an object of Shape Rounded Rectangle
        Shape shape1 = shapeFactory.getShape("RECTANGLE");
        //call draw method of Shape Rectangle
        shape1.draw();
        //get an object of Shape Rounded Square
        Shape shape2 = shapeFactory.getShape("SQUARE");
        //call draw method of Shape Square
        shape2.draw();
        //get rounded shape factory
        AbstractFactory shapeFactory1 = FactoryProducer.getFactory(true);
        //get an object of Shape Rectangle
        Shape shape3 = shapeFactory1.getShape("RECTANGLE");
        //call draw method of Shape Rectangle
        shape3.draw();
        //get an object of Shape Square
        Shape shape4 = shapeFactory1.getShape("SQUARE");
        //call draw method of Shape Square
        shape4.draw();
    }

}
