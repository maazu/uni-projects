package cethreetwenty.two.ViewPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class TimePanel extends JPanel {

    private static JLabel label;

    public TimePanel() {
        label = new JLabel("",
                JLabel.CENTER);
        new Timer(1000, e ->
        {
            LocalDateTime now = LocalDateTime.now();
            String time = String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
            label.setText(time);

        }).start();
    }

    public JLabel CreateTimeLabel() {
        label.setFont(new Font("Monospaced", Font.BOLD, 65));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        return label;
    }

    public JLabel getcurrentTime() {
        return label;
    }
}


