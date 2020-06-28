import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class clsCalendarEvents {

    /** Description of changes, made to the CalendarQuickstart.java
     *  The, created by Google, CalendarQuickstart.java file with small extras
     *
     *  I basically copied the CalendarQuickstart.java file of the GoogleAPI
     *  and added some methods(addEvent, timeParser)
     *  To be able to work with the Events which I get from the "getEvents" method,
     *  the List of Events is returned. This is the only change I made to the original methods.
     */

    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static List<Event> getEvents(DateTime timeMin, DateTime timeMax) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // List the next 10 events from the primary calendar.

        Events events = service.events().list("primary")
                .setTimeMin(timeMin)
                .setTimeMax(timeMax)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();

        //the Events are still printed to the console, because I think it is nice to see, if there are just no events, or if the program failed to execute the desired way

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
        return items; //return the List of all Events to work with it in clsMain
    }



    /** Description of addEvent()
     * The addEvent method takes an ArrayList of Events (eventsOut) and return an ArrayList of Strings (events)
     *  The method just adds all Information of an event, which is provided by the ArrayList of events (eventsOut)
     *  and adds them as Strings to the ArrayList of Strings (events)
     *  This is done for every event in eventsOut
     *
     * @param eventsOut
     * @param events
     */
    public static void addEvent(ArrayList<Event> eventsOut, ArrayList<String> events){


        String lS = System.lineSeparator();
        for (Event e:eventsOut //check, if there is no information given. If not, just take the empty string. Just to be sure, that no Object is null, because this could throw a Exception
        ) {
            String location;
            String summary;
            String description;

            if(e.getLocation()==null)
                  location= "";
            else
                location = e.getLocation();

            if(e.getSummary()==null)
                summary = "";
            else
                summary = e.getSummary();

            if(e.getDescription()==null)
                description = "";
            else
                description = e.getDescription();



            String tmp = //add the information to the ArrayList events
                "BEGIN:VEVENT" + lS +
                "UID:" + e.getId() + lS +
                "LOCATION:"+location + lS +
                "SUMMARY:"+summary + lS+
                "DESCRIPTION:"+description+lS+
                "DTSTART:"+timeParser(e.getStart().toString()) +lS+
                "DTEND:"+timeParser(e.getEnd().toString()) +lS+
                "END:VEVENT"
                ;

            events.add(tmp);

        }
        /** debugging only
                for (Event e:eventsOut
                     ) {
                    System.out.println(e.getSummary());

                }

                for (String e:events
                     ) {
                    System.out.println(e);

            }
         **/




    }

    /** Description of timeParser()
     * Format the input of timeMin and timeMax, so they match the RFC3339_PATTERN
     *
     * @param dT_time
     * @return
     */

    public static String timeParser(String dT_time) {


        String[] split = dT_time.split("\"");
        String replace = split[3].replace("-", "").replace(":", "").replace(".", "");

        /**
         * debuggin only

              for (String s : split
            ) {
                System.out.println(s);

            }
            System.out.println(replace);

         **/

    return replace;
    }

}
