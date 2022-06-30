package cethreetwenty.two.ViewPanel;

import org.junit.Before;
import org.junit.Test;
import java.awt.*;

import static java.time.LocalDateTime.now;
import static org.junit.Assert.assertEquals;

public class TimePanelTest {
    private TimePanel timePanel;

    @Before
    public void setTimeTest(){
        timePanel  = new TimePanel();
    }

    @Test
    public void TimeTest() {
        assertEquals(String.valueOf(now().getHour()+":"+now().getMinute()+":"+now().getSecond()), timePanel.getcurrentTime().getText());
        assertEquals(new Font("Monospaced", Font.BOLD, 65), timePanel.CreateTimeLabel().getFont());
    }

}
