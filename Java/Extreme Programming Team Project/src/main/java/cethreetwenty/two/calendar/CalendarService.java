package cethreetwenty.two.calendar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;

public class CalendarService
{
    protected static final String JSON_CREDENTIALS_PATH = "/credentials.json";

    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    protected static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);

    protected static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final String APPLICATION_NAME = "speeclock";

    private Calendar calendar;

    public CalendarService() { }

    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) {
        Credential appCredential = null;
        try (InputStream in = CalendarService.class.getResourceAsStream(JSON_CREDENTIALS_PATH))
        {
            final GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            GoogleAuthorizationCodeFlow googleAuthFlow = new Builder(HTTP_TRANSPORT, JSON_FACTORY, secrets, SCOPES)
                    .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                    .setAccessType("online")
                    .build();
            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
            appCredential = new AuthorizationCodeInstalledApp(googleAuthFlow, receiver).authorize("user");
        }
        catch (IOException e) {
//            getLogger().log(Level.SEVERE, "Failed to initialize google calendar API credentials.", e);
            e.printStackTrace();
        }
        return appCredential;
    }

    public Calendar setUp()
    {
        Thread t = new Thread(() ->{
            NetHttpTransport httpTransport = null;
            try {
                httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Credential credential = getCredentials(httpTransport);
            calendar = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        });
        t.start();
        return calendar;
    }
}
