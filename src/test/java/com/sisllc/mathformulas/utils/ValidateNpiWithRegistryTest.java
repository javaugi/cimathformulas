/*
 * Copyright (C) 2021 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.sisllc.mathformulas.utils;

import com.abc.utils.OpenCsvNpiValidation;
import com.test.ProjectTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 *
 *
 * @author bill
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ValidateNpiWithRegistryTest extends ProjectTest {

    private static final Logger log = LoggerFactory.getLogger(ValidateNpiWithRegistryTest.class);

    @Autowired
    Environment env;

    @Value("${validNpiValue}")
    public String validNpiValue;
    @Value("${invalidNpiValue}")
    public String invalidNpiValue;

    @Test
    public void validNPI() {
        boolean isValidNPI = OpenCsvNpiValidation.validateNPIWithRegistry(validNpiValue);
        Assert.assertTrue(isValidNPI);
    }

    @Test
    public void invalidNPI() {
        boolean isValidNPI = OpenCsvNpiValidation.validateNPIWithRegistry(invalidNpiValue);
        Assert.assertTrue(!isValidNPI);
    }
}
