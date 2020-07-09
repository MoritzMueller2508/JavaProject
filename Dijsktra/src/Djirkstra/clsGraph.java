package Djirkstra;

import java.util.ArrayList;

/** The class clsGraph consist out of two ArrayList, one for all edges and one for each Vertexes
 * @param <T>
 */
public class clsGraph<T> {

    final ArrayList<clsEdge<T>> edges;
    final ArrayList<clsVertex<T>> vertex;

    /** Constructor
     *
     * @param edges
     * @param vertex
     */
    public clsGraph(ArrayList<clsEdge<T>> edges, ArrayList<clsVertex<T>> vertex) {
        this.edges = edges;
        this.vertex = vertex;
    }
}
