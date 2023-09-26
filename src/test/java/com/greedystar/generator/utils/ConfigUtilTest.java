package com.greedystar.generator.utils;

import com.greedystar.generator.context.ProjectContext;
import org.junit.Assert;
import org.junit.Test;


public class ConfigUtilTest {

    @Test
    public void testGetConfiguration() {
        ProjectContext projectContext = ConfigUtil.getConfiguration();
        Assert.assertNotNull(projectContext);
        System.out.println(projectContext);
    }

    public void testSetConfiguration() {

    }
}