package Djirkstra;

/** The class clsEdge consist out of a startVertex, an endVertex and a distance/value
 * @param <T>
 */
public class clsEdge<T> {

    private clsVertex<T> start;
    private clsVertex<T> end;
    private int distance;


    /** Konstruktor
     *
     *
     * @param start
     * @param end
     * @param distance
     */
    public clsEdge(clsVertex<T>start, clsVertex<T> end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    /** These methods are getter and setter for all the attributes in clsEdge
     *
     * @return
     */

    public T getStart() {
        return start.getName();
    }

    public void setStart(clsVertex<T> start) {
        this.start = start;
    }

    public T getEnd() {
        return end.getName();
    }

    public void setEnd(clsVertex<T> end) {
        this.end = end;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    /** DebugTool
     */
    public void printEdge(){

        System.out.println("Edge is "+ getStart() +" " + getEnd() +" " +getDistance());


    }
}
