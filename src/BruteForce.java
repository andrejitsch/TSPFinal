import java.util.ArrayList;
import java.util.Collections;

/**
 * Project TravellingSalesmanProblem This class is used to implement the BruteForce-Algorithm
 *
 * @Author Andrej Drobin
 * @Author Deniz Kücüktas
 * @Author Julian Geerdes
 * @Date 24.08.2018
 * @Version 1.1 Last Change: 28.09.2018
 */
public class BruteForce
{

    ArrayList<Route> shortestRoutes = new ArrayList<Route>();



    /**
     * Used to permute the Nodes for the BruteForceAlgorithm
     *
     * @param x
     * @param currentRoute
     * @param shortestRoute
     * @return
     */

    public ArrayList<Route> permuteNodes(int x, Route currentRoute, Route shortestRoute)
    {
        currentRoute.getNodes().stream().filter(y -> currentRoute.getNodes().indexOf(y) >= x)
                .forEach(y ->
                {
                    int indexOfY = currentRoute.getNodes().indexOf(y);
                    Collections.swap(currentRoute.getNodes(), indexOfY, x);
                    permuteNodes(x + 1, currentRoute, shortestRoute);
                    Collections.swap(currentRoute.getNodes(), x, indexOfY);
                });

        if (x == currentRoute.getNodes().size() - 1)
        {
            if ((int) calculateTotalDistance(currentRoute) <=
                    (int) calculateTotalDistance(shortestRoute))
            {
                shortestRoute.getNodes().clear();
                shortestRoute.getNodes().addAll(currentRoute.getNodes());
                addToShortestRoutes(new Route(shortestRoute));
            }
        }
        return shortestRoutes;
    }



    /**
     * Calculates the Distance of the Route
     *
     * @param route
     * @return totalDistance
     */

    public int calculateTotalDistance(Route route)
    {
        int amountNodes = route.getNodes().size();

        return (int) (route.getNodes().stream().mapToDouble(x ->
        {
            int nodeIndex = route.getNodes().indexOf(x);
            double returnValue = 0;
            if (nodeIndex < amountNodes - 1)
            {
                returnValue = x.measureDistanceForBruteForce(route.getNodes().get(nodeIndex + 1));
            }

            return returnValue;
        }).sum() + route.getNodes().get(0)
                .measureDistanceForBruteForce(route.getNodes().get(amountNodes - 1)));
    }



    /**
     * @param route
     * @return String of the Distance
     */

    public String getTotalDistance(Route route)
    {
        String returnValue = Integer.toString(calculateTotalDistance(route));
        if (returnValue.length() == 4)
        {
            returnValue = " " + returnValue;
        } else if (returnValue.length() == 3)
        {
            returnValue = " " + returnValue;
        }
        return returnValue;
    }



    /**
     * checks whether the new Route is shorter than the old shortest route
     *
     * @param route
     */

    public void addToShortestRoutes(Route route)
    {
        shortestRoutes.removeIf(
                x -> (int) calculateTotalDistance(x) > (int) calculateTotalDistance(route));
        shortestRoutes.add(route);
    }
}
