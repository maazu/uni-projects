package cethreetwenty.two.settings;

import java.awt.*;
import java.awt.event.*;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import cethreetwenty.two.SoundSettings.SoundSettingsTab;
import cethreetwenty.two.TimeSettings.TimeSettingsTab;
import cethreetwenty.two.calendar.CalendarPanel;

public class SettingsPanel extends JPanel
{
    Checkbox checkBox1;
    private JTabbedPane settingsTabs;
    public TimeSettingsTab timeSettingsTab;
    public SoundSettingsTab soundSettingsTab;


//  Icons made by monkik from www.flaticon.com
    public SettingsPanel() {
        setupJPanel();
        createComponents();
        assignComponentsToPanel();
    }

    private void assignComponentsToPanel() {
        add(settingsTabs);
    }

    private void createComponents() {

        timeSettingsTab = new TimeSettingsTab();
        settingsTabs = new JTabbedPane();
        settingsTabs.addTab("Time Settings",timeSettingsTab);

        CalendarPanel calenderTab = new CalendarPanel();

        settingsTabs.addTab("Calendar Settings", calenderTab);

        soundSettingsTab = new SoundSettingsTab();

        settingsTabs.addTab("Sound Settings", soundSettingsTab);
    }

    private void setupJPanel() {
        setLayout(new GridLayout(2,1));
        setSize(new Dimension(350, 400));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(Color.white);
    }
}

