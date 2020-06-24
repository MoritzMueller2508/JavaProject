package Djirkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class clsDijkstraManager<T> implements maxInteger {

   final HashMap<clsVertex<T>, Integer> neighbours = new HashMap<clsVertex<T>, Integer>()  {}; //new HashMap to store the Nodes and the distance
   final ArrayList<clsVertex<T>> visited = new ArrayList<clsVertex<T>>();

    public void dijkstra(clsGraph<T> graph, clsVertex<T> startNode) {

        initialize(startNode);

        ArrayList<clsVertex<T>> Q = graph.vertex;
        ArrayList<clsEdge<T>> E = graph.edges;



        while (Q.size() != 0){
                clsVertex<T> currentNode = Q.get(findSmallestValue(Q)); //currentNode is set
            //System.out.println(currentNode.getValue());
                findNeighbours(currentNode, E, Q); //find all Neighbours | here, the public hashMap neighbours is updated

            for (Map.Entry<clsVertex<T>, Integer> item :neighbours.entrySet()  //iterate through the hashMap
                 ) {clsVertex<T> key = item.getKey();
                    Integer value = item.getValue();


                    int alternativ = currentNode.getValue() + value;


                    if (alternativ < key.getValue()){           // compare the existing value of the node with the alternative value
                        //System.out.println(alternativ);       debugging purpose
                        key.setValue(alternativ);
                        key.setBefore(currentNode);

                    }

            }
            //System.out.println(Q.size());

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

    private void initialize(clsVertex<T> startNode){ //sets the value of the startNode to 0

        startNode.setValue(0);

    }


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

    public void findShortestPath(clsVertex<T> destination){


        ArrayList<clsVertex<T>> path = new ArrayList<clsVertex<T>>();


        clsVertex<T> currentNode = destination;
        path.add(currentNode);

        while(currentNode.getBefore()!=null){
            currentNode = currentNode.getBefore();
            path.add(0,currentNode);



        }

        for (clsVertex<T> step:path
             ) {
            System.out.println(step.getName());

        }
        System.out.println(destination.getValue());
    }


    public ArrayList<clsVertex<T>> returnVisted(){
        return visited;

    } //just to access visited in the main

            
}
        












