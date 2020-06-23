package Djirkstra;

public class clsEdge<T> {

    private clsVertex<T> start;
    private clsVertex<T> end;
    private int distance;

    public clsEdge(clsVertex<T>start, clsVertex<T> end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

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


    public void printEdge(){

        System.out.println("Edge is "+ getStart() +" " + getEnd() +" " +getDistance());


    }
}
