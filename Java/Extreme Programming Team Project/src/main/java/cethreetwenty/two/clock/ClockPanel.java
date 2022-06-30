package cethreetwenty.two.clock;

import static cethreetwenty.two.ui.design.UIConstants.CLOCK_PANEL_J_FRAME_BACKGROUND_COLOUR;
import static cethreetwenty.two.ui.design.UIConstants.CLOCK_PANEL_J_FRAME_LAYOUT;
import static cethreetwenty.two.ui.design.UIConstants.CLOCK_PANEL_J_FRAME_PREFERRED_SIZE_DIMENSION;

import javax.swing.*;

import cethreetwenty.two.ViewPanel.DatePanel;
import cethreetwenty.two.ViewPanel.GreetingPanel;
import cethreetwenty.two.ViewPanel.TimePanel;


public class ClockPanel extends JPanel {
    private TimePanel time = new TimePanel();

    private GreetingPanel greet =  new GreetingPanel("default");

    private DatePanel datePanel = new DatePanel();

    private JLabel currentTime = time.CreateTimeLabel(), dateText = datePanel.getDate(), greetMesg = greet.setUpGreetingLabel();

    public ClockPanel() {
        setupJPanel();
        assignJLabelsToPanel();
    }

    private void setupJPanel() {
        setLayout(CLOCK_PANEL_J_FRAME_LAYOUT);
        setPreferredSize(CLOCK_PANEL_J_FRAME_PREFERRED_SIZE_DIMENSION);
        setBackground(CLOCK_PANEL_J_FRAME_BACKGROUND_COLOUR);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    private void assignJLabelsToPanel() {
        add(greetMesg);
       //TextToSpeech.textToSpeech(greetMesg.getText()); // can be implemented in the clockPanel
        System.out.println(greetMesg);
        add(currentTime);
        add(dateText);
    }
}
