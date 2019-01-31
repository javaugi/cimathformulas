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
/*
Step 1 Create an interface Item representing food item and packing.
Step 2 Create concrete classes implementing the Packing interface.
Step 3 Create abstract classes implementing the item interface providing default functionalities.
Step 4 Create concrete classes extending Burger and ColdDrink classes
Step 5 Create a Meal class having Item objects defined above.
Step 6 Create a MealBuilder class, the actual builder class responsible to create Meal objects.
Step 7 BuiderPatternDemo uses MealBuider to demonstrate builder pattern.

 */
public class BuilderPattern {

    private static final Logger log = LoggerFactory.getLogger(BuilderPattern.class);

    public static void main(String[] args) {

        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }

}
