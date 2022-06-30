package cethreetwenty.two.settings;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class SettingsPanelTest
{
    private SettingsPanel testSettingsPanel;

    @Before
    public void setUp() {
        testSettingsPanel = new SettingsPanel();
    }

    @Test
    public void setupJPanelTest() {
        assertEquals(400, testSettingsPanel.getHeight());
        assertEquals(350, testSettingsPanel.getWidth());
        assertEquals(Color.white, testSettingsPanel.getBackground());
    }
}
