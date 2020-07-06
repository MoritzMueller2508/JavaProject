import com.google.api.services.calendar.model.Event;

import java.util.ArrayList;
import java.util.List;


public class clsFilter {

    /** Description of filter
     *
     *  This method returns an ArrayList, only with the desired events
     *  The events are filtered by the provides SelectionCriteria in clsMain (args[2])
     *  Filter uses the String with the formatted regEx expressions (by clsMain.regExParser())
     *
     * @param aL
     * @param s
     * @return
     */
    public static ArrayList<Event> filter(List<Event> aL, String s){
    ArrayList<Event> newEvents= new ArrayList<>();
    String summary = "";

    //iterate over all events and just add the filtered events to a new ArrayList
    for (Event event:aL
         ) {if (event.getSummary()!=null)
             summary = event.getSummary();

             if(summary.matches(s))
                 newEvents.add(event);

    }

return newEvents; //return only the filtered events
}

}
