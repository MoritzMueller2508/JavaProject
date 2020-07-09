package Djirkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Description of clsDijkstraManager
 *
 * The clsDijkstraManager manages the general Algorithm.
 * It initializes the graph, finds the smalles Node and has a method to find the shortest path
 *
 * @param <T>
 */
public class clsDijkstraManager<T> implements maxInteger {

   final HashMap<clsVertex<T>, Integer> neighbours = new HashMap<clsVertex<T>, Integer>()  {}; //new HashMap to store the Nodes and the distance
   final ArrayList<clsVertex<T>> visited = new ArrayList<clsVertex<T>>();

    /** General Algorithm
     *
     *
     *
     * @param graph
     * @param startNode
     */
    public void dijkstra(clsGraph<T> graph, clsVertex<T> startNode) {

        initialize(startNode);

        ArrayList<clsVertex<T>> Q = graph.vertex;
        ArrayList<clsEdge<T>> E = graph.edges;



        while (Q.size() != 0){
                clsVertex<T> currentNode = Q.get(findSmallestValue(Q)); //currentNode is set

                findNeighbours(currentNode, E, Q); //find all Neighbours | here, the public hashMap neighbours is updated

            for (Map.Entry<clsVertex<T>, Integer> item :neighbours.entrySet()  //iterate through the hashMap
                 ) {clsVertex<T> key = item.getKey();
                    Integer value = item.getValue();


                    int alternativ = currentNode.getValue() + value;


                    if (alternativ < key.getValue()){           // compare the existing value of the node with the alternative value
                        key.setValue(alternativ);
                        key.setBefore(currentNode);

                    }

            }


                visited.add(currentNode);
                Q.remove(currentNode);


        }

        /**     for debuggin purpose only
        for (clsVertex t:debug
             ) {if(t.getName().equals(startNode.getName()))
            System.out.println("startNode");
             else
                 t.printVertex();

        }**/




    }

    /** Description of initialize(clsVertex<T> startNode)
     *
     * sets the value of the startNode to 0
     *
     * @param startNode
     */
    private void initialize(clsVertex<T> startNode){ //sets the value of the startNode to 0

        startNode.setValue(0);

    }

    /** Description of findSmallestValue(ArrayList<clsVertex<T>> Q)
     *
     * finds the Vertex with the smallest value and sets this as current Vertex
     * Furthermore, it returns the index of the smallest Vertex in the ArrayList, so we can access the Vertex
     *
     * @param Q
     * @return
     */
    private int findSmallestValue(ArrayList<clsVertex<T>> Q){ //finds the smallest value in the ArrayList of Nodes and sets the Node with the "smallest value" as currentNode

        int min = maxInteger.integerMaxValue;
        int minIndex = 0;

        for (int i=0; i <Q.size(); i++){ //iterate though the ArrayList of Nodes and update min, minIndex, if there is a value smaller then before

            if (Q.get(i).getValue() < min){
                min = Q.get(i).getValue();
                minIndex = i;}


        }
        return minIndex; //return the index of the smallest Node, so we can access it in the ArrayList of all Nodes
    }

    /** Finds all Neighbours by iterating through the ArrayList and compares the names of the start, end-Vertex of all Edges
     *
     * @param currentNode
     * @param E
     * @param Q
     */
    private void findNeighbours(clsVertex<T> currentNode, ArrayList<clsEdge<T>> E, ArrayList<clsVertex<T>> Q){ //find all neighbours of the currentNode, as long as they are in the set of remaining Nodes
        neighbours.clear();


        for (clsEdge<T> edge:E     //for every edge in the set of edges, search for the edges, where the start is equal to the currentNodes name
             ) {


             if (edge.getStart().equals(currentNode.getName())){


                for (clsVertex<T> vertex:Q     //if the name is equal, we iterate through all nodes, to get the index of the specific Node
                ) {
                    if (edge.getEnd().equals(vertex.getName()))
                        neighbours.put(vertex,edge.getDistance()); //afterwards we add the node with the assigned value to the HashMap

                }
            }

        }
        /**                 //for debugging purpose only//
        currentNode.printVertex();
        neighbours.forEach((key, value) -> System.out.println(key.getName() + " | " + value));
         **/
    }

    /** Iterate backwards through the ArrayList of all visited Nodes, the find the best path
     *  Take the "Vorgänger" as next Node, until you reached a Node with no "Vorgänger"
     *
     *
     * @param destination
     */
    public void findShortestPath(clsVertex<T> destination){


        ArrayList<clsVertex<T>> path = new ArrayList<clsVertex<T>>();


        clsVertex<T> currentNode = destination;     //sets destination as current Node
        path.add(currentNode);      //adds the current Node to the path

        while(currentNode.getBefore()!=null){       //while the current Node has a "Vorgänger", the loop will continue
            currentNode = currentNode.getBefore();
            path.add(0,currentNode);

        //in general, iterate through the ArrayList of all added Nodes backwards, until you reach the startNode

        }

        for (clsVertex<T> step:path
             ) {
            System.out.println(step.getName());

        }
        System.out.println(destination.getValue());     //prints the  shortest path
    }


}
        












