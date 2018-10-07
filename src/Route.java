import java.util.ArrayList;
import java.util.Arrays;

/**
 * Project TravellingSalesmanProblem This class is used to create Routes for the Brute-Force
 * Algorithm
 *
 * @Author Andrej Drobin
 * @Author Deniz Kücüktas
 * @Author Julian Geerdes
 * @Date 24.08.2018
 * @Version 1.1 Last Change: 28.09.2018
 */
public class Route
{
    private ArrayList<Nodes> nodes = new ArrayList<Nodes>();



    /**
     * This is a constructor of the class Route. Set the nodes of the ArrayList initialRoute.
     *
     * @param initialRoute needs an ArrayList for the routes.
     */
    public Route(ArrayList<Nodes> initialRoute)
    {
        setNodes(initialRoute);
    }



    /**
     * This is the constructor of the class Route. The routes will be created.
     *
     * @param route needs a route.
     */
    public Route(Route route)
    {
        route.nodes.stream().forEach(x -> nodes.add(x));
    }



    /**
     * Sets the nodes.
     *
     * @param nodes needs a ArrayList of Nodes.
     */
    public void setNodes(ArrayList<Nodes> nodes)
    {
        this.nodes = nodes;
    }



    /**
     * Gets the nodes of the route.
     *
     * @return a node.
     */
    public ArrayList<Nodes> getNodes()
    {
        return nodes;
    }



    /**
     * A toString method to print the ArrayList.
     *
     * @return the arrayList of nodes.
     */
    @Override public String toString()
    {
        return Arrays.toString(nodes.toArray());
    }
}
