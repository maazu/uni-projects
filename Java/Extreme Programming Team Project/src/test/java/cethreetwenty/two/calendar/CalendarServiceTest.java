package cethreetwenty.two.calendar;

import static cethreetwenty.two.calendar.CalendarService.JSON_FACTORY;
import static cethreetwenty.two.calendar.CalendarService.getCredentials;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Before;
import org.junit.Test;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;

public class CalendarServiceTest
{
    private NetHttpTransport httpTransport;
    private Calendar calendar;
    private Credential credential;

    private static final String APPLICATION_NAME = "speeclock";

    @Before
    public void setUp() throws GeneralSecurityException, IOException
    {
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        credential = getCredentials(httpTransport);
        calendar = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @Test
    public void testGetCredentials() throws IOException
    {
        assertNotNull(httpTransport);
        assertNotNull(credential);
        assertNotNull(calendar);
        assertEquals(APPLICATION_NAME, calendar.getApplicationName());

        Events events = calendar.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(new DateTime(System.currentTimeMillis()))
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        assertNotNull(events);
        events.getItems().forEach(e -> {
            DateTime time = e.getStart().getDateTime();
            System.out.println(e.getSummary() + " " + time);
        });
    }
}