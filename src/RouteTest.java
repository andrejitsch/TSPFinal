import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Project TravellingSalesmanProblem This class is used to test the class Route
 *
 * @Author Andrej Drobin
 * @Author Deniz Kücüktas
 * @Author Julian Geerdes
 * @Date 24.08.2018
 * @Version 1.1 Last Change: 28.09.2018
 */
public class RouteTest
{
    Route route;
    private ArrayList<Nodes> nodes = new ArrayList<Nodes>();



    @Before public void testBefore()
    {
        nodes.add(new Nodes(0, "Osna", 100, 120));
        nodes.add(new Nodes(1, "Dortmund", 100, 120));
    }

}