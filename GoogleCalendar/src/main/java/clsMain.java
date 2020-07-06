import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class clsMain {

    //initialize global variables

    public static ArrayList<String> events = new ArrayList<>();
    public static String path = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException, GeneralSecurityException {


        List<Event> eventsOut = clsCalendarEvents.getEvents(dateTimeParserMin(args[1]), dateTimeParserMax(args[2]));

        /** debugging only (printing args[0] - args[4] + filter)
         *
         *  System.out.println("----------------------\n" +args[0]);
         *  System.out.println(args[1]);
         *  System.out.println(args[2]);
         *  System.out.println(args[3]);
         *  System.out.println(args[4] + "\n ------------------------");
         *
         *  System.out.println("filter is"+ filter);
         */


        String filter = regExParser(args[3]);



        if(clsFilter.filter(eventsOut,filter).size()==0)
            throw new Error("unfortunately, we are not able to find any upcoming events with your desired specification.");

        clsCalendarEvents.addEvent( clsFilter.filter(eventsOut,filter), events);

        /** debugging only (printing the elements of the filtered list)
         *
         * System.out.println("\n ---------------------------------");
         *  for (String e:events
         *  ) {
         *  System.out.println(e);
         *  }
         *  System.out.println("\n-------------------------------------");
         */


        clsExport<String> exporter = new clsExport<>();
        exporter.export(args[4],path,events);


    }

    /** Decription of dateTimeParserMin/Max
     *
     *  parse the input of clsMain, so it can be used by the programm (DateTime)
     *
     * @param str
     * @return
     */
    private static DateTime dateTimeParserMin(String str) {

        String date=str+"T00:00:00Z";

        return DateTime.parseRfc3339(date);
    }
    private static DateTime dateTimeParserMax(String str) {



        String date=str+"T29:59:59Z";

        return DateTime.parseRfc3339(date);
    }

    /** Description of reExParser
     *
     *  To use regEx in our project, we have to replace all * with .*, so we can use regEx properly
     *
     * @param str
     * @return
     */
    private static String regExParser(String str){
        String s = "";
        if (str.equals("xxxx"))
            str = "*";

        if (str.contains("*"))
            s = str.replace("*", ".*");
        else
            return str;

        return s;
    }







}
