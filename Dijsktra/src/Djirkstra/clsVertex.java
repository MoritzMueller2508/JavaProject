package Djirkstra;

public class clsVertex<T> {

    private T name;
    private int value;
    private clsVertex<T> before;



    public clsVertex(T name, int value) {
        this.name = name;
        this.value = value;
        this.before = null;
    }

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

    public void printVertex(){
        System.out.println(getName());
        System.out.println(getValue());
        System.out.println(getBefore().getName());

    }


}
