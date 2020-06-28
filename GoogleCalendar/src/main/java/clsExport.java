import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

/**Description of clsExport
 *
 * clsExport is the general "Exporter" of our file.
 * In this class, the file is written and exported.
 *
 * @param <T>
 */
public class clsExport<T> implements BuildUp_iCal {

    /** Description of export
     *  A new file is created and the information are added to the file
     *
     *
     * @param fileName  specifies the name of the file (args[4])
     * @param pathName  specifies the path of the file (path selected or workingDirecory)
     * @param events    all events which have to be added
     */
    public void export(String fileName, String pathName, ArrayList<T> events) {

        File file = new File(pathName, fileName); //create the new file
        try{
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }

        try{

            //write the data into the file

            writeBasicData(pathName, fileName);
            writeEvents(pathName, fileName, events);
            writeEnd(pathName, fileName);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /** Description of writeBasicData
     *
     * add the basic information, provided in the interface BuildUp_iCal, to the file
     *
     * @param pathName
     * @param fileName
     */
    private void writeBasicData(String pathName, String fileName) {
        try {
            FileWriter writer = new FileWriter(pathName+"\\"+fileName);

            //iterate over all Strings in the ArrayList provided by the interface
            for (String s : buildUp
            ) {
                writer.write(s + System.lineSeparator());

            }


            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /** Description of writeEvents
     *
     *  add all events which have to be added to the file
     *
     * @param pathName
     * @param fileName
     * @param events
     * @throws IOException
     */
    public void writeEvents(String pathName, String fileName, ArrayList<T> events) throws IOException {
        FileWriter writer = new FileWriter(pathName+"\\"+fileName, true);


        //iterate over the ArrayList with all events and the information for these events (normally an ArrayList of Strings)
        try{
            for (T e:events
            ) {writer.write(e + System.lineSeparator());

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /** Description of writeEnd
     *
     *  just adds the end-line to the .ics file
     *
     * @param pathName
     * @param fileName
     * @throws IOException
     */
    public void writeEnd(String pathName, String fileName) throws IOException {
        FileWriter writer = new FileWriter(pathName+"\\"+fileName, true);

        try{
            writer.write("END:VCALENDAR");
            writer.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }



    }
}
