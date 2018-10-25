/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.sisllc.mathformulas.impl;

import com.sisllc.mathformulas.impl.PrimeNumbers;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ProductOfPrimesBetweenLToRTest {

    PrimeNumbers pn = null;

    @Before
    public void setup() {
        pn = new PrimeNumbers();
    }

    @Test
    public void when3to5Then15() {
        Assert.assertTrue(15 == pn.product(3, 5));
    }

    @Test
    public void when37Then105() {
        System.out.println("product=" + pn.product(3, 7));
        Assert.assertTrue(105 == pn.product(3, 7));
    }
}
