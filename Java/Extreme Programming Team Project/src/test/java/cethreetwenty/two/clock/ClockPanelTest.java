package cethreetwenty.two.clock;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Dimension;

import org.junit.Before;
import org.junit.Test;

public class ClockPanelTest
{
    private ClockPanel testClockPanel;

    @Before
    public void setUp() {
        testClockPanel = new ClockPanel();
    }

    @Test
    public void setupJPanelTest() {
        assertEquals(new Dimension(400, 400), testClockPanel.getPreferredSize());
        assertEquals(Color.white, testClockPanel.getBackground());
    }
}
