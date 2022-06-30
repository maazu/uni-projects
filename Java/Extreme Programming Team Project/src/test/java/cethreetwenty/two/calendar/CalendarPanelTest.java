package cethreetwenty.two.calendar;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;


public class CalendarPanelTest
{
    private CalendarPanel testCalendarPanel;

    @Before
    public void setUp()
    {
        testCalendarPanel = new CalendarPanel();
    }

    @Test
    public void setupJPanelTest()
    {
        assertNotNull(testCalendarPanel.addCalendarButton);
        testCalendarPanel.addCalendarButton.doClick();
    }
}