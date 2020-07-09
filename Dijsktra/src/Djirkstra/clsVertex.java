package Djirkstra;

/** The class clsVertex consists out of a name, a value and a "Vorgänger", the Node, which was visited before
 *
 *
 * @param <T>
 */
public class clsVertex<T> {

    private T name;
    private int value;
    private clsVertex<T> before;


    /** Constructor
     *
     *
     * @param name
     * @param value
     */
    public clsVertex(T name, int value) {
        this.name = name;
        this.value = value;
        this.before = null;
    }

    /** Getter and Setter
     *
     * @return
     */
    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public clsVertex<T> getBefore() { return before; }

    public void setBefore(clsVertex<T> before) { this.before = before; }

    /**DebugTool
     *
     */
    public void printVertex(){
        System.out.println(getName());
        System.out.println(getValue());
        System.out.println(getBefore().getName());

    }


}
