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
import org.junit.Test;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class PrimeNumbersTest {

    @Test
    public void when1ThenTrue() {
        PrimeNumbers pn = new PrimeNumbers();
        Assert.assertTrue(pn.isPrime(1));
    }

    @Test
    public void when2ThenTrue() {
        PrimeNumbers pn = new PrimeNumbers();
        Assert.assertTrue(pn.isPrime(2));
    }

    @Test
    public void when3ThenTrue() {
        PrimeNumbers pn = new PrimeNumbers();
        Assert.assertTrue(pn.isPrime(3));
    }

    @Test
    public void when4ThenFalse() {
        PrimeNumbers pn = new PrimeNumbers();
        Assert.assertTrue(!pn.isPrime(4));
    }
}
