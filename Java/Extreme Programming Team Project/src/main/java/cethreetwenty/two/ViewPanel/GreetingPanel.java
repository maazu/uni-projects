package cethreetwenty.two.ViewPanel;

import static java.time.LocalDateTime.now;

import java.awt.*;

import javax.swing.*;

public class GreetingPanel extends JPanel {
    private static JLabel greetingLabel;

    public GreetingPanel(String moment) {
        createGreetingLabel(moment);
    }

    private void createGreetingLabel(String moment){
        greetingLabel = new JLabel(moment);
    }

    public JLabel setUpGreetingLabel() {
        greetingLabel.setText(" Hello User, " + greetingMessage(now().getHour()));
        greetingLabel.setFont(new Font("", Font.PLAIN, 24));
        greetingLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        greetingLabel.setHorizontalAlignment(JLabel.CENTER);
        return greetingLabel;
    }

    //Message based on 24 hours clock system
    String greetingMessage(int dayTime) {
        String tempTime = "";

        if (dayTime < 12 && dayTime > 5) {
            tempTime = "Good Morning ";
        } else if (dayTime >= 12 && dayTime < 18) {
            tempTime = "Good Afternoon ";
        } else if (dayTime < 20 && dayTime > 17) {
            tempTime = "Good Evening ";
        } else {
            tempTime = "Good Night ";
        }
        return tempTime;
    }
}