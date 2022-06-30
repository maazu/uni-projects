package cethreetwenty.two.application;

import static cethreetwenty.two.ui.design.Messages.APPLICATION_J_FRAME_TITLE;
import static cethreetwenty.two.ui.design.UIConstants.APPLICATION_J_FRAME_WIDTH_X_AXIS;
import static cethreetwenty.two.ui.design.UIConstants.APPLICATION_J_FRAME_WIDTH_Y_AXIS;

import java.awt.*;

import javax.swing.JFrame;

import cethreetwenty.two.clock.ClockPanel;
import cethreetwenty.two.settings.SettingsPanel;

public class ApplicationFrame extends JFrame {

    private ClockPanel clockPanel;
    private SettingsPanel settingsPanel;

    public ApplicationFrame() {
        setupJFrame();
        createJPanels();
        addJPanelsToJFrame();
    }

    private void addJPanelsToJFrame() {
        add(clockPanel, BorderLayout.WEST);
        add(settingsPanel);
    }

    private void createJPanels() {
        clockPanel = new ClockPanel();
        settingsPanel = new SettingsPanel();
        settingsPanel.setBackground(Color.darkGray);
    }

    private void setupJFrame() {
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(APPLICATION_J_FRAME_TITLE);
        setSize(APPLICATION_J_FRAME_WIDTH_X_AXIS, APPLICATION_J_FRAME_WIDTH_Y_AXIS);
    }
}
