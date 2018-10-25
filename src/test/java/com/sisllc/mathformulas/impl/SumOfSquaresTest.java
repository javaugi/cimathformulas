/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.sisllc.mathformulas.impl;

import com.sisllc.mathformulas.impl.SumOfSquares;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class SumOfSquaresTest {

    @Test
    public void when1Then1() {
        SumOfSquares sos = new SumOfSquares();
        Assert.assertTrue(1 == sos.sum(1));
    }

    @Test
    public void when2Then5() {
        SumOfSquares sos = new SumOfSquares();
        Assert.assertTrue(5 == sos.sum(2));
    }

    @Test
    public void when3Then14() {
        SumOfSquares sos = new SumOfSquares();
        Assert.assertTrue(14 == sos.sum(3));
    }

    @Test
    public void when3RecursiveThen14() {
        SumOfSquares sos = new SumOfSquares();
        System.out.println("sos.sumRecursive(3)" + sos.sumRecursive(3));
        Assert.assertTrue(14 == sos.sumRecursive(3));
    }
}
