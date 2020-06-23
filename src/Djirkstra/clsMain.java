/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Djirkstra;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;


public class clsMain implements maxInteger {


    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in); //System in as a standard input stream



        ArrayList<clsEdge<String>> distance = initialize().getValue();
        ArrayList<clsVertex<String>> nodes = initialize().getKey();

        System.out.println("Please input your startNode and destination. \nThe following nodes are available for selection: \n");
        printArrayList(nodes);
        System.out.println("\nstartingNode: (The city have to start with a capital letter!)");
        String startPoint = input.nextLine();
        clsVertex<String> startNode = getStartingPoint(startPoint, nodes);
        System.out.println("\ndestination: (The city have to start with a capital letter!)");
        String dest = input.nextLine();
        clsVertex<String> destination = getDestination(dest,nodes);





        if(startNode == destination)            //if the destination equals the startinPoint, we do not have to compute dijsktra and can save performance
            throw new Exception("You are already at the destination. There is no need for travel");


        clsDijkstraManager<String> algorithm = new clsDijkstraManager<String>();
        clsGraph<String> graph = new clsGraph<String>(distance, nodes);

        algorithm.dijkstra(graph,startNode );
        ArrayList<clsVertex<String>> visited = algorithm.visited;
/**         debuggin only
        for (clsVertex<String> d:visited
             ) {if (d.getBefore()==null)
            System.out.println("startNode");
             else
            d.printVertex();

        }**/
        System.out.println("\n");

        algorithm.findShortestPath(destination);









    }

    private static Pair<ArrayList<clsVertex<String>>, ArrayList<clsEdge<String>>> initialize(){

        String[] cities = {"Frankfurt", "Mannheim", "Karlsruhe", "Augsburg", "Muenchen", "Kassel", "Nuernberg", "Stuttgart", "Wuerzburg", "Erfurt"};
        String[] routes = {"Frankfurt Mannheim 85", "Mannheim Karlsruhe 80", "Karlsruhe Augsburg 250", "Augsburg Muenchen 84", "Muenchen Kassel 502", "Muenchen Nuernberg 167" ,
                "Kassel Frankfurt 173", "Frankfurt Wuerzburg 217", "Wuerzburg Erfurt 186", "Wuerzburg Nuernberg 103", "Nuernberg Stuttgart 183"};


        ArrayList<clsVertex<String>> vertexObjects = new ArrayList<clsVertex<String>>(); //Creating an ArrayList of Objects from type clsVertex for every String in cities[]

        for (String city : cities) {

            vertexObjects.add(new clsVertex<String>(city.toString(), integerMaxValue));

        } /**
         for (clsVertex object: vertexObjects //for debug purpose only
         ) { object.printVertex();

         } **/

        //end initializing vertexObjects


        ArrayList<clsEdge<String>> edgeObjects = new ArrayList<clsEdge<String>>(); //Creating an ArrayList of Objects from type clsEdge for every String in cities[]

        for (String route : routes) { //Create an clsEdge object for every String in routes, consisting out of startingpoint, endpoint and distance between them

            String s = route.toString();

            //System.out.println("String is "+s); //for debugging only


            String[] objects = s.split(" "); //split the first string into substrings, split at all blanks
            String start = objects[0];
            String end = objects[1];
            String distance_ = objects[2];
            int distance = Integer.parseInt(distance_);

            //System.out.println(start + end + distance_); //for debugging only

            label1:
            //initialize label to break two loops at once when the desired start- and endpoints are available
            for (int j = 0; j < vertexObjects.size(); j++) { //iterate through all created vertexObjects to find start and endpoint
                if (vertexObjects.get(j).getName().equals(start)) {
                    for (clsVertex<String> vertexObject : vertexObjects) {
                        if (vertexObject.getName().equals(end)) {
                            edgeObjects.add(new clsEdge<String>(vertexObject, vertexObjects.get(j), distance));
                            edgeObjects.add(new clsEdge<String>(vertexObjects.get(j), vertexObject, distance)); //add a new clsEdge object to the ArrayList with <start, end, distance> for each route
                            break label1; //break two loops at once, so you can start with the next route
                        }
                    }

                }

            }


        }
                                            //debugging
        /**
        for (clsEdge edge:edgeObjects
         ) {
         edge.printEdge();
         System.out.println("\n");


         }
        System.out.println(edgeObjects.size());
         **/

        return new Pair<ArrayList<clsVertex<String>>, ArrayList<clsEdge<String>>>(vertexObjects, edgeObjects);

    }

    private static void printArrayList(ArrayList<clsVertex<String>> nodes){

        for (clsVertex<String> node:nodes
             ) {
                System.out.println(node.getName());

        }
    }

    private static clsVertex<String> getStartingPoint(String sP, ArrayList<clsVertex<String>> nodes){
        Error error = new Error("The city you selected as startPoint does not exist");

        for (clsVertex<String> n:nodes
             ) {if (n.getName().equals(sP))
                 return n;

        }
        throw error;

    }

    private static clsVertex<String> getDestination(String dT, ArrayList<clsVertex<String>> nodes){
        Error error = new Error("The city you selected as destination does not exist");

        for (clsVertex<String> n:nodes
        ) {if (n.getName().equals(dT))
            return n;

        }
        throw error;

    }

}
