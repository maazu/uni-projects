package cethreetwenty.two.TimeSettings;

import java.awt.*;
import java.util.Set;

import javax.swing.*;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeSettingsTab extends JPanel {
   private JLabel SYSTEM_TIME_LABEL;
   private JComboBox SELECT_COUNTRY = new JComboBox();
   private JLabel TimeFormatLabel;
   private JComboBox TIME_FORMAT_OPTIONS = new JComboBox();
   private JLabel DATE_FORMAT_LABEL;
   private JComboBox DATE_FORMAT_OPTIONS = new JComboBox();

   private JLabel timezoneTimeOutputLabel;

   public TimeSettingsTab() {
       setUpLayout();
       createTabComponents();
       createActionListeners();
   }

    private void createActionListeners() {
        SELECT_COUNTRY.addActionListener(event -> {
         /*  new Timer(1080, e ->{ }
            Note:
            Updating the time every is printing unstoppable time , I am commenting it out, however if you can fix please proceed otherwise*/
            timezoneTimeOutputLabel.setText("Date and Local Time" + RegionalTime.getTime(SELECT_COUNTRY.getSelectedItem().toString()));
            RegionalTime.getTime(SELECT_COUNTRY.getSelectedItem().toString());
        });

        TIME_FORMAT_OPTIONS.addActionListener(event -> {
            timezoneTimeOutputLabel.setText(RegionalTime.convertTime(SYSTEM_TIME_LABEL.getText(), (String) TIME_FORMAT_OPTIONS.getSelectedItem()));

        });


        DATE_FORMAT_OPTIONS.addActionListener(event -> {
            timezoneTimeOutputLabel.setText(RegionalTime.formatDate((String) DATE_FORMAT_OPTIONS.getSelectedItem()));
        });
    }

    private void setUpLayout() {
       setVisible(true);
       setLayout(new GridLayout(7,1));
   }

   private void createTabComponents() {
       SYSTEM_TIME_LABEL= new JLabel();
       SYSTEM_TIME_LABEL.setText(RegionalTime.getCurrentTime());
       timezoneTimeOutputLabel = new JLabel("", SwingConstants.CENTER);
       final JLabel timezoneLabel = new JLabel("Time zone ");
       // get the all the zoneIds list in the dropdown
       final Set<String> timezoneIds = DateTimeZone.getAvailableIDs();
       final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("ZZ");

       for (String zoneId : timezoneIds) {
           String offset = dateTimeFormatter.withZone(DateTimeZone.forID(zoneId)).print(0);
           SELECT_COUNTRY.addItem(String.format("(UTC%s) %s", offset, zoneId));
       }

       TimeFormatLabel = new JLabel("Time Display Format ");
       TIME_FORMAT_OPTIONS.addItem("12 Hours");
       TIME_FORMAT_OPTIONS.addItem("24 Hours");

       DATE_FORMAT_LABEL = new JLabel("Date Display Format ");
       DATE_FORMAT_OPTIONS.addItem("yyyy-M-dd");
       DATE_FORMAT_OPTIONS.addItem("dd-MM-yyyy");
       DATE_FORMAT_OPTIONS.addItem("d MMMM, yyyy");
       DATE_FORMAT_OPTIONS.addItem("d MMM, yyyy");

       add(TimeFormatLabel, BorderLayout.NORTH);
       add(TIME_FORMAT_OPTIONS, BorderLayout.NORTH);
       add(timezoneLabel, BorderLayout.CENTER);
       add(SELECT_COUNTRY);
       add(DATE_FORMAT_LABEL);
       add(DATE_FORMAT_OPTIONS);
       add(timezoneTimeOutputLabel);
   }
}

