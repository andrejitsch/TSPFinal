import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project TravellingSalesmanProblem This class is used to test the Class Edges
 *
 * @Author Andrej Drobin
 * @Author Deniz Kücüktas
 * @Author Julian Geerdes
 * @Date 28.05.2018
 * @Version 1.1 Last Change: 28.09.2018
 */
public class EdgesTest
{
    Edges edges;



    @Before public void setUp() throws Exception
    {
        Nodes a = new Nodes(0, "Bawinkel", 50, 100);
        Nodes b = new Nodes(1, "Haselünne", 100, 200);

        edges = new Edges(a, b, 4);
    }



    @Test public void testEdges() throws Exception
    {
        Edges expected =
                new Edges(new Nodes(2, "Lingen", 50, 100), new Nodes(3, "Meppen", 100, 200), 2);

        edges.setSourceNode(new Nodes(2, "Lingen", 50, 100));
        edges.setTargetNode(new Nodes(3, "Meppen", 100, 200));

        assertEquals(expected.getSourceNode().getNumber(), edges.getSourceNode().number);
        assertEquals(expected.getTargetNode().getNumber(), edges.getTargetNode().number);
    }



    @Test public void getWeight() throws Exception
    {
        int expected = 4;

        assertEquals(expected, edges.weight, 0.001);
    }



    @Test public void testToString() throws Exception
    {
        String expected =
                edges.getSourceNode().toString() + " ------> " + edges.getTargetNode().toString() +
                        ", Weight: " + edges.weight;

        assertEquals(expected, edges.toString());
    }



    @Test public void testEqualt() throws Exception
    {
        Nodes a = new Nodes(0, "Bawinkel", 50, 100);
        Nodes b = new Nodes(1, "Haselünne", 100, 200);

        Edges testEdge = new Edges(b, a, 2);
        Edges testEdge2 = new Edges(a, b, 1);

        assertTrue(testEdge2.equals(testEdge) && testEdge.equals(testEdge2));
        assertTrue(testEdge2.hashCode() == testEdge.hashCode());
    }
}