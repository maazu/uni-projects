package cethreetwenty.two.ViewPanel;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static java.time.LocalDateTime.now;
import static org.junit.Assert.*;

public class GreetingPanelTest {

    private GreetingPanel greetingPanel;

    @Before
    public void setUpGreetingPanel(){
        greetingPanel = new GreetingPanel("default");
    }

    @Test
    public void testSetUpGreetingLabel(){
        assertEquals(new Font("", Font.PLAIN, 24), greetingPanel.setUpGreetingLabel().getFont());
        assertEquals(JLabel.CENTER, greetingPanel.setUpGreetingLabel().getHorizontalAlignment());
        assertEquals(" Hello User, " + greetingPanel.greetingMessage(now().getHour()), greetingPanel.setUpGreetingLabel().getText());
    }

    @Test
    public void testGreetingMessage(){
        assertEquals("Good Morning ", greetingPanel.greetingMessage(6));
        assertEquals("Good Afternoon ", greetingPanel.greetingMessage(13));
        assertEquals("Good Evening ", greetingPanel.greetingMessage(19));
        assertEquals("Good Night ", greetingPanel.greetingMessage(21));
    }
}
