package cethreetwenty.two.ViewPanel;

import static java.time.LocalDateTime.now;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class DatePanelTest {
    private DatePanel datePanel;

    @Before
    public void setUpDatePanel(){
        datePanel = new DatePanel();
    }
    @Test
    public void testCurrentDateLabel() {
        assertEquals(now().getDayOfMonth()+" "+now().getMonth()+" "+now().getYear(), datePanel.getDate().getText());
        assertEquals(new Font("", Font.PLAIN, 30), datePanel.getDate().getFont());
    }


}
