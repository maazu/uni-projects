package cethreetwenty.two.SoundSettings;

import cethreetwenty.two.utility.TextToSpeech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.time.LocalTime;
import java.net.URL;

public class SoundSettingsTab extends JPanel implements ActionListener {


    public static boolean soundOn = true;
    private JCheckBox soundCheckBox;
    private JLabel soundIcon;
    private JSlider timeIntervalSlider;
    private JLabel timeIntervalLabel;
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int INIT = 50;

    public static int soundVolume = INIT;

    private Thread intervalThread;
    private int intervalTime;


    private URL muteIcon = this.getClass().getResource("/Images/volume-off-indicator.png");
    //Icons made by Googlefrom www.flaticon.com
    private URL volumeIcon = this.getClass().getResource("/Images/volume-on-indicator.png");
    //Icons made by Googlefrom www.flaticon.com

    private ImageIcon volumeOff = new ImageIcon(muteIcon);
    private ImageIcon volumeOn = new ImageIcon(volumeIcon);

    private String[] langStrings = {"English" , "Russian", "Chinese" , "Japanese"};
    private JComboBox<String> lanList = new JComboBox<>(langStrings);
    static public String lan = "English";

    public SoundSettingsTab() {
        createComponents();
        checkBoxListener();
        sliderListener();
        setupSettingsTab();


    }

    private void setupSettingsTab() {
        setSize(50, 50);
        setVisible(true);
    }

    private void createComponents() {
        soundIcon = new JLabel(volumeOff);
        soundCheckBox = new JCheckBox("Enable Sound");
        add(soundIcon, BorderLayout.NORTH);
        add(soundCheckBox, BorderLayout.NORTH);

        timeIntervalSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        intervalTime = INIT*60000;
        timeIntervalSlider.setMajorTickSpacing(20);
        timeIntervalSlider.setMinorTickSpacing(5);
        timeIntervalSlider.setPaintTicks(true);
        timeIntervalSlider.setPaintLabels(true);
        add(timeIntervalSlider, BorderLayout.SOUTH);
        add(lanList, BorderLayout.SOUTH);
        lanList.addActionListener(this);

        timeIntervalLabel = new JLabel(String.valueOf(INIT));
        add(timeIntervalLabel, BorderLayout.SOUTH);
    }


    private void sliderListener() {
        timeIntervalSlider.addChangeListener(e -> {
            intervalTime = timeIntervalSlider.getValue() * 60000;
            timeIntervalLabel.setText(String.valueOf(timeIntervalSlider.getValue()));
        });

    }

    private void checkBoxListener() {
        soundCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                soundOn = true;
                intervalThread = new Thread(this::handleIntervalThread);
                intervalThread.start();
                soundIcon.setIcon(volumeOn);
            } else {
                soundOn = false;
                intervalThread.interrupt();
                soundIcon.setIcon(volumeOff);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JComboBox cb = (JComboBox)actionEvent.getSource();
        String langList = (String)cb.getSelectedItem();
        TextToSpeech.setLanguageSelected(langList);
    }

    private void handleIntervalThread() {
        try {
            while (true) {
                Thread.sleep(intervalTime);
                final LocalTime time = LocalTime.now();
                final int hour = time.getHour();
                final int minutes = time.getMinute();
                final int seconds = time.getSecond();
                while (true){
                    if(seconds == 10 || seconds == 20||seconds == 30 || seconds == 40||seconds == 50 || seconds == 00){
                        if (lan.equals("English")) {
                            TextToSpeech.textToSpeech("The time is " + String.format("%02d", hour) + " " + String.format("%02d", minutes));
                            System.out.println("Language selected in Sound: " + lan);

                        }
                        else if (lan.equals("Russian")) {
                            TextToSpeech.textToSpeech("время сейчас " + String.format("%02d", hour) + " " + String.format("%02d", minutes));
                            System.out.println("Language selected in Sound: " + lan);
                        }
                        else if (lan.equals("Chinese")) {
                            TextToSpeech.textToSpeech("現在的時間是 " + String.format("%02d", hour) + " " + String.format("%02d", minutes));
                            System.out.println("Language selected in Sound: " + lan);
                        }
                        else if (lan.equals("Japanese")) {
                            TextToSpeech.textToSpeech("時間は " + String.format("%02d", hour) + " " + String.format("%02d", minutes));
                            System.out.println("Language selected in Sound: " + lan);
                        }
                    }
                }
               // TextToSpeech.textToSpeech("The time is " + String.format("%02d", hour) + " " + String.format("%02d", minutes));


            }
        } catch (InterruptedException e) {
            System.out.println("Interval thread stopped");
        }
    }
}



