package cethreetwenty.two.ViewPanel;

import javax.swing.*;

import java.awt.*;

import static java.time.LocalDateTime.now;

public class DatePanel {

    private static final JLabel currentDate = new JLabel( now().getDayOfMonth()+" "+now().getMonth()+" "+now().getYear(),
            JLabel.CENTER);

    public JLabel getDate() {
        currentDate.setFont(new Font("", Font.PLAIN, 30));
        currentDate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        return currentDate;
    }
}
