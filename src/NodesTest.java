import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project TravellingSalesmanProblem This class is used to test the class Nodes
 *
 * @Author Andrej Drobin
 * @Author Deniz Kücüktas
 * @Author Julian Geerdes
 * @Date 28.05.2018
 * @Version 1.1 Last Change: 28.09.2018
 */
public class NodesTest
{

    Nodes nodes;



    @Before public void setUp() throws Exception
    {
        nodes = new Nodes(0, "Lingen", 150, 200);

        assertNotNull(nodes);
    }



    @Test public void testNumber() throws Exception
    {
        nodes.setNumber(1);
        int expected = 1;

        assertEquals(expected, nodes.getNumber());
    }



    @Test public void testName() throws Exception
    {
        String expected = "Lingen";

        assertEquals(expected, nodes.getName());
    }



    @Test public void testXpos() throws Exception
    {
        nodes.setXpos(100);
        int expected = 100;

        assertEquals(expected, nodes.getXpos());
    }



    @Test public void testYpos() throws Exception
    {
        nodes.setYpos(400);
        int expected = 400;

        assertEquals(expected, nodes.getYpos());
    }



    @Test public void testInTour() throws Exception
    {
        nodes.setInTour(true);
        boolean expected = true;

        assertEquals(expected, nodes.getInTour());
    }



    @Test public void testToString() throws Exception
    {
        String expected = "Node" + nodes.getNumber() + ":  " + " X: " + nodes.getXpos() + ", Y: " +
                nodes.getYpos();

        assertEquals(expected, nodes.toString());
    }



    @Test public void testFirstNode()
    {
        nodes.setFirstNode(false);

        assertFalse(nodes.getFirstNode());
    }



    @Test public void testNext()
    {
        nodes.setNext(1);

        assertEquals(1, nodes.getNext());
    }



    @Test public void testDistance()
    {
        double expected = 13.038404810405298;
        assertEquals(expected, nodes.measureDistanceForBruteForce(new Nodes(2, "Freren", 160, 10)),
                0.001);
    }
}

