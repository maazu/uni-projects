package cethreetwenty.two.application;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ApplicationFrameTest
{
    private ApplicationFrame testFrame;

    @Before
    public void setUp() {
        testFrame = new ApplicationFrame();
    }

    @Test
    public void testSetFrameParameters() {
        assertTrue(testFrame.isVisible());
        assertFalse(testFrame.isResizable());
        assertEquals(EXIT_ON_CLOSE, testFrame.getDefaultCloseOperation());
        assertEquals(800, testFrame.getWidth());
        assertEquals(400, testFrame.getHeight());
        assertEquals("Talking Clock", testFrame.getTitle());
    }
}
