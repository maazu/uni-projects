package cethreetwenty.two.calendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

import static cethreetwenty.two.ui.design.UIConstants.CLOCK_PANEL_J_FRAME_PREFERRED_SIZE_DIMENSION;

public class CalendarPanel extends JPanel
{

    private boolean calendarAdded = false;
    //    private CalendarRange range;
    private JComboBox dropDown;
    protected JButton addCalendarButton;
    //    private JTextArea eventsText;
//    private JScrollPane eventsPane;
    private JTable eventsTable;
    private JLabel emailaddress;
    private DefaultTableModel tableModel;
    private Calendar calendar;
    private Events events;

    public CalendarPanel()
    {
        setupJPanel();
        GridBagConstraints c = new GridBagConstraints();

        addCalendarButton = setupButton();
        c.fill = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        add(addCalendarButton, c);

        emailaddress = new JLabel();
        c.gridx = 0;
        c.gridy = 1;
        add(emailaddress, c);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Event Name");
        tableModel.addColumn("Date/Time");
        eventsTable = new JTable(tableModel);
        eventsTable.getColumnModel().getColumn(0).setPreferredWidth(175);
        eventsTable.getColumnModel().getColumn(1).setPreferredWidth(175);
        c.gridx = 0;
        c.gridy = 2;
        add(eventsTable.getTableHeader(), c);
        c.gridx = 0;
        c.gridy = 3;
        add(eventsTable, c);

    }

    private void setupJPanel()
    {
        setSize(CLOCK_PANEL_J_FRAME_PREFERRED_SIZE_DIMENSION);
        setLayout(new GridBagLayout());
    }

    private JButton setupButton()
    {
        JButton button = new JButton("+ Calendar");
        button.addActionListener(e ->
        {
            calendarAdded = buttonHandler();
        });
//        button.setBackground(GENERAL_BUTTON_COLOUR);
        return button;
    }

    private void setupEventsList(DefaultTableModel tableModel)
    {
        System.out.println("in eventsList");
        System.out.println(events.getItems());
        for (Event e : events.getItems())
        {
            DateTime time = e.getStart().getDateTime();
            System.out.println(e.getSummary());
//            eventsText.append(e.getSummary() + " " + time + "\n");
            tableModel.addRow(new Object[]{e.getSummary(), time});
        }
    }

    private boolean buttonHandler()
    {
        boolean b;
        System.out.println("starting button handler");
        CalendarService calendar = new CalendarService();
        calendar.setUp();
        b = true;
        events = getCalendarInfo();
        System.out.println("button was pressed");
        setupEventsList(tableModel);
        emailaddress.setText(events.getSummary());
        addCalendarButton.setEnabled(false);
        return b;
    }

    private Events getCalendarInfo()
    {
        Events events = null;
        try {
            events = calendar.events().list("primary")
                    .setMaxResults(10)
                    .setTimeMin(new DateTime(System.currentTimeMillis()))
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }
}
