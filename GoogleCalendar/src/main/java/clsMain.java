import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;



public class clsMain {

    //initialize global variables

    public static ArrayList<String> events = new ArrayList<>();
    public static String path = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException, GeneralSecurityException {


        List<Event> eventsOut = clsCalendarEvents.getEvents(dateTimeParserMin(args[0]), dateTimeParserMax(args[1]));

        /** debugging only (printing args[0] - args[3] + filter)
         *
         *  System.out.println("----------------------\n" +args[0]);
         *  System.out.println(args[1]);
         *  System.out.println(args[2]);
         *  System.out.println(args[3]+ "\n ------------------------"););
         *
         *
         *  System.out.println("filter is"+ filter);
         */






        String filter = regExParser(args[2]);


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
        exporter.export(args[3],path,events);


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
        if (str.equals("xxxx")) //if the input is "xxxx", then every event will be displayed
            str = "*";

        if (str.contains("*"))
            s = str.replace("*", ".*");
        else
            return str;

        return s;
    }







}
