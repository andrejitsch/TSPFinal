import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project TravellingSalesmanProblem This class is used to test the class TravellingSalesmanProblem
 *
 * @Author Andrej Drobin
 * @Author Deniz Kücüktas
 * @Author Julian Geerdes
 * @Date 28.05.2018
 * @Version 1.1 Last Change: 28.09.2018
 */
public class TravellingSalesmanProblemTest
{

    TravellingSalesmanProblem salesmanProblem;



    @Before public void setUp()
    {
        salesmanProblem = new TravellingSalesmanProblem(6);
    }



    @Test public void testTravellingSalesmanProblem()
    {
        salesmanProblem = new TravellingSalesmanProblem(6);
    }



    @Test public void testCreateGivenGraph()
    {
        salesmanProblem.createGivenGraphForTesting();

        assertEquals(salesmanProblem.nodes[3], salesmanProblem.returnNode(3));
        assertEquals(salesmanProblem.edges[0], salesmanProblem.returnEdge(0));
    }



    @Test public void testGetAmountNodes()
    {
        assertEquals(6, salesmanProblem.getAmountNodes());
    }



    @Test public void testGetAmountEdges()
    {
        int expected = 36;

        assertEquals(36, salesmanProblem.getAmountEdges());
    }



    @Test public void testGetDistance()
    {
        int n1 = 1;
        int n2 = 2;
        double expected = 47.0;

        salesmanProblem.createGivenGraphForTesting();

        assertEquals(expected, salesmanProblem.calculateDistance(n1, n2), 0.001);
    }



    @Test public void testGetNextNode()
    {
        salesmanProblem.createGivenGraphForTesting();
        assertEquals(4, salesmanProblem.nextNode(0));
    }



    @Test public void testNearestNeighbour()
    {
        salesmanProblem.fillRandom();
        salesmanProblem.nearestNeighbour(0);

        assertTrue(salesmanProblem.nodes[0].getInTour());
    }



    @Test public void testAllCost()
    {
        double expected = 395.0;

        salesmanProblem.createGivenGraphForTesting();

        assertEquals(expected, salesmanProblem.allCost(), 0.001);
    }

}

