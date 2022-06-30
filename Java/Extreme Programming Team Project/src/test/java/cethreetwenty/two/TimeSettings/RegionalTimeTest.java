package cethreetwenty.two.TimeSettings;

import static cethreetwenty.two.TimeSettings.RegionalTime.evaluateCountry;
import static cethreetwenty.two.TimeSettings.RegionalTime.getCurrentTime;
import static cethreetwenty.two.TimeSettings.RegionalTime.getTime;

import org.junit.Assert;
import org.junit.Test;

public class RegionalTimeTest
{
    @Test
    public void testGetTime() {
        getTime("Europe/Lisbon");
    }

    @Test
    public void testGetCurrentTime() {
        System.out.println(getCurrentTime());
    }

    @Test
    public void testEvaluateCountry() {
        Assert.assertEquals(evaluateCountry("Europe/Lisbon"), "Europe/Lisbon");
    }
}
