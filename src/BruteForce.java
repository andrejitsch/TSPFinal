import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by AS on 06.10.2018.
 */
public class BruteForce
{

    ArrayList<Route> shortestRoutes = new ArrayList<Route>();

    public ArrayList<Route> permuteNodes(int x, Route currentRoute, Route shortestRoute)
    {
        currentRoute.getNodes().stream().filter(y -> currentRoute.getNodes().indexOf(y) >= x).forEach(y ->
        {
            int indexOfY = currentRoute.getNodes().indexOf(y);
            Collections.swap(currentRoute.getNodes(), indexOfY, x);
            permuteNodes(x + 1, currentRoute, shortestRoute);
            Collections.swap(currentRoute.getNodes(), x, indexOfY);
        });

        if (x == currentRoute.getNodes().size() -1)
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

    public int calculateTotalDistance(Route route)
    {
        int amountNodes = route.getNodes().size()-1;

        return (int) (route.getNodes().stream().mapToDouble(x ->
        {
            int nodeIndex = route.getNodes().indexOf(x);
            double returnValue = 0;
            if (nodeIndex<amountNodes-1)
            {
                returnValue = x.measureDistanceForBruteForce(route.getNodes().get(nodeIndex + 1));
            }

            return returnValue;
        }).sum() + route.getNodes().get(0).measureDistanceForBruteForce(route.getNodes().get(amountNodes)));
    }

    public String getTotalDistance(Route route)
    {
        String returnValue = Integer.toString(calculateTotalDistance(route));
        if (returnValue.length() == 4)
        {
            returnValue = " " + returnValue;
        }else if (returnValue.length() == 3)
        {
            returnValue = " " + returnValue;
        }
        return returnValue;
    }

    public void addToShortestRoutes(Route route)
    {
        shortestRoutes.removeIf(x -> (int) calculateTotalDistance(x) > (int) calculateTotalDistance(route));
        shortestRoutes.add(route);
    }
}
