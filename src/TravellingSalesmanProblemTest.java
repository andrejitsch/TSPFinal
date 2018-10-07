import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by AS on 07.10.2018.
 */
public class TravellingSalesmanProblemTest
{

    TravellingSalesmanProblem salesmanProblem;

    @Before
    public void setUp()
    {
        salesmanProblem = new TravellingSalesmanProblem(6);
    }

    @Test
    public void testTravellingSalesmanProblem()
    {
        salesmanProblem = new TravellingSalesmanProblem(6);
    }

    @Test
    public void testCreateGivenGraph()
    {
        salesmanProblem.createGivenGraph();

        assertEquals(salesmanProblem.nodes[3], salesmanProblem.returnNode(3));
        assertEquals(salesmanProblem.edges[0], salesmanProblem.returnEdge(0));
    }


    @Test
    public void testGetAmountNodes()
    {
        assertEquals(6, salesmanProblem.getAmountNodes());
    }

    @Test
    public void testGetAmountEdges()
    {
        int expected = 36;

        assertEquals(36, salesmanProblem.getAmountEdges());
    }

    @Test
    public void  testGetDistance()
    {
        int n1 = 1;
        int n2 = 2;
        double expected= 47.0;

        salesmanProblem.createGivenGraph();

        assertEquals(expected,salesmanProblem.calculateDistance(n1,n2), 0.001);
    }

    @Test
    public void testGetNextNode()
    {
        salesmanProblem.createGivenGraph();
        assertEquals(4, salesmanProblem.nextNode(0));
    }

    @Test
    public void testNearestNeighbour()
    {
       salesmanProblem.fillRandom();
        salesmanProblem.nearestNeighbour(0);

        assertTrue(salesmanProblem.nodes[0].getInTour());
    }

    @Test
    public void testAllCost()
    {
        double expected = 395.0;

        salesmanProblem.createGivenGraph();

        assertEquals(expected,salesmanProblem.allCost(),0.001);
    }



}

