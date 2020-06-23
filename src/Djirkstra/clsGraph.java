package Djirkstra;

import java.util.ArrayList;

public class clsGraph<T> {

    final ArrayList<clsEdge<T>> edges;
    final ArrayList<clsVertex<T>> vertex;

    public clsGraph(ArrayList<clsEdge<T>> edges, ArrayList<clsVertex<T>> vertex) {
        this.edges = edges;
        this.vertex = vertex;
    }
}
