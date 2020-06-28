import java.util.ArrayList;

/** Description of BuildUp_iCal
 *
 * this is an interface with the basic "beginning" Strings of the .ics file
 *
 */
public interface BuildUp_iCal {
    //the beginning is saved in an ArrayList, which we use to iterate over the Strings. We could have used a String instead, but I preferred this variant

     ArrayList<String> buildUp = new ArrayList<String>(){
         {
             add("BEGIN:VCALENDAR");
             add("VERSION:2.0");
             add("PROID:Moritz_iCalProject");
             add("METHOD:PUBLISH");
         }
     };

}
