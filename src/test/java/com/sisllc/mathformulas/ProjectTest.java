package com.sisllc.mathformulas;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties", "classpath:applicationtest.properties"})
public class ProjectTest {

    @Value("${com.ciminc.testProfile}")
    public String testProfile;

    @Value("${npiRegistryUrl}")
    public String npiRegistryUrl;
    @Value("${validNpiValue}")
    public String validNpiValue;
    @Value("${invalidNpiValue}")
    public String invalidNpiValue;

    @Autowired
    Environment env;

    public ProjectTest() {
        super();
    }

    @Test
    public void checkTestSetup() {
        Assert.assertTrue(StringUtils.isNotEmpty(npiRegistryUrl));
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
