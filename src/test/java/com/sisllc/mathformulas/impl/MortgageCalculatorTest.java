/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.sisllc.mathformulas.impl;

import com.sisllc.mathformulas.impl.MortgageCalculator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class MortgageCalculatorTest {

    @Test
    public void whenTerm30YearsThen360Months() {
        MortgageCalculator calc = new MortgageCalculator();
        Assert.assertTrue(360 == calc.termInMonths(30));
    }

    @Test
    public void whenRate6Then0dot005() {
        MortgageCalculator calc = new MortgageCalculator();
        Assert.assertTrue(0.005 == calc.rateInMonths(6));
    }

    @Test
    public void whenRate6Term1Then0dot005() {
        MortgageCalculator calc = new MortgageCalculator();
        System.out.println("payment=" + calc.printPayments(30, 200000, 6));
        Assert.assertTrue(1199.1 == calc.printPayments(30, 200000, 6));
    }
}
