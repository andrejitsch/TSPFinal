import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

/**
 * Project TravellingSalesmanProblem This class is used to initiate the Graph
 *
 * @Author Andrej Drobin
 * @Author Deniz Kücüktas
 * @Author Julian Geerdes
 * @Date 28.05.2018
 * @Version 1.1 Last Change: 28.09.2018
 */

public class TravellingSalesmanProblem
{
    Nodes[] nodes;
    Edges[] edges;



    /**
     * This is the constructor of the class TravellingSalesmanProblem. The constructor multiplies
     * the nodes array, and take this size for the edges array.
     *
     * @param nodesLength needs the amount of nodes.
     */
    public TravellingSalesmanProblem(int nodesLength)
    {
        this.nodes = new Nodes[nodesLength];
        this.edges = new Edges[nodesLength * nodesLength];
    }



    /**
     * This method is used to randomly create a Graph
     */
    public void fillRandom()
    {
        Random r = new Random();

        for (int i = 0; i < nodes.length; i++)
        {
            int xPos = r.nextInt(1024);
            int yPos = r.nextInt(800);

            nodes[i] = new Nodes(i, "Node" + i, xPos, yPos);

        }

        createEdges();

    }



    /**
     * This method is used to convert an ArrayList of Nodes into an Array of Nodes
     *
     * @param nodesA needs an ArrayList of Nodes
     */

    public void convertNodes(ArrayList<Nodes> nodesA)
    {

        for (int i = 0; i < nodes.length; i++)
        {
            nodes[i] = new Nodes(nodesA.get(i).getNumber(), nodesA.get(i).getName(),
                    nodesA.get(i).getXpos(), nodesA.get(i).getYpos());
        }

    }



    /**
     * This method is used to create all Edges between Nodes
     */

    public void createEdges()
    {
        int x = 0;
        int d = 1;
        for (int i = 0; i < nodes.length - 1; i++)
        {
            for (int j = d; j < nodes.length; j++)
            {
                edges[x] = new Edges(nodes[i], nodes[j], calculateDistance(i, j));
                x++;
            }
            d++;
        }
    }



    /**
     * Gets the weight of the Edges.
     *
     * @return the weight of the Edge.
     */
    public int getWeight(double cost)
    {
        int index = 0;

        if (cost >= 0 && cost < 50)
        {
            index = 1;
        }
        if (cost >= 50 && cost < 100)
        {
            index = 2;
        }

        if (cost >= 100 && cost < 150)
        {
            index = 3;
        }

        if (cost >= 150 && cost < 200)
        {
            index = 4;
        }
        if (cost >= 200 && cost < 250)
        {
            index = 5;
        }
        if (cost >= 250 && cost < 300)
        {
            index = 6;
        }
        if (cost >= 300 && cost < 350)
        {
            index = 7;
        }
        if (cost >= 350 && cost < 400)
        {
            index = 8;
        }
        if (cost >= 400 && cost < 450)
        {
            index = 9;
        }
        if (cost >= 450 && cost < 500)
        {
            index = 10;
        }
        if (cost >= 500 && cost < 550)
        {
            index = 11;
        }
        if (cost >= 550 && cost < 600)
        {
            index = 12;
        }
        if (cost >= 600 && cost < 650)
        {
            index = 13;
        }
        if (cost >= 650 && cost < 700)
        {
            index = 14;
        }
        if (cost >= 700 && cost < 750)
        {
            index = 15;
        }
        if (cost >= 750 && cost < 800)
        {
            index = 16;
        }
        if (cost >= 800)
        {
            index = 17;
        }

        return index;
    }



    /**
     * This method calculates the distance between two Nodes.
     *
     * @param n1 needs a number for example of a Node.
     * @param n2 needs a number for example of a Node.
     * @return the distance between this Nodes.
     */
    public double calculateDistance(int n1, int n2)
    {
        double dx, dy, dx2, dy2;

        dx = nodes[n1].getXpos() - nodes[n2].getXpos();
        dy = nodes[n1].getYpos() - nodes[n2].getYpos();
        dx2 = (dx * dx) / 10;
        dy2 = (dy * dy) / 10;

        return Math.floor(Math.sqrt(dx2 + dy2));
    }



    /**
     * This method determines the closest node.
     *
     * @param n searching of the closest neighbour of n.
     * @return the closest neighbour that is not yet in the tour.
     */
    public int nextNode(int n)
    {
        double dist, min = 10000;
        int index = -1;

        int start = 0;

        while (start < getAmountNodes())
        {
            while ((start < getAmountNodes()) && nodes[start].getInTour())
            {
                start++;
            }

            if (start >= getAmountNodes())
            {
                break;
            }

            dist = calculateDistance(n, start);
            if ((start != n) && controlEdges(start, n) && dist < min)
            {
                min = dist;
                index = start;
            }

            start++;
        }

        return index;
    }



    /**
     * Control if the edge exist that the needed for the algo.
     *
     * @param s needs the number of the node.
     * @param n needs the number of the node.
     * @return true  if exists else false.
     */
    private boolean controlEdges(int s, int n)
    {
        Edges controlEdges = new Edges(nodes[s], nodes[n], getWeight(calculateDistance(s, n)));
        Edges controlEdges2 = new Edges(nodes[n], nodes[s], getWeight(calculateDistance(s, n)));

        boolean success = false;

        for (int i = 0; i < getAmountEdges(); i++)
        {

            if (controlEdges.equals(edges[i]) || controlEdges2.equals(edges[i]))
            {
                success = true;
                break;
            } else
            {
                success = false;
            }
        }

        return success;
    }



    /**
     * This method saves the next Node from the Method nextNode. The boolean value of inTour will be
     * set on true, for the visited nodes. The first node is always zero(default setting). The last
     * node connect with the first node.
     */
    public void nearestNeighbour(int firstNode)
    {
        int s = firstNode;
        nodes[s].setFirstNode(true);

        for (int i = 0; i < getAmountNodes() - 1; i++)
        {
            nodes[s].setInTour(true);
            nodes[s].setNext(nextNode(s));
            s = nodes[s].getNext();
        }
        nodes[s].setInTour(true);
        nodes[s].setNext(firstNode);
    }



    /**
     * This method is used to reset all Nodes to not be visited, so the Algorithm can be performed
     * more than once on the same Graph
     */

    public void resetTour()
    {
        for (int i = 0; i < getAmountNodes(); i++)
        {
            nodes[i].setInTour(false);
        }
    }



    /**
     * @return The total Distance of the Tour
     */

    public double allCost()
    {

        double length = 0;
        for (int i = 0; i < getAmountNodes() - 1; i++)
        {
            length += calculateDistance(i, nodes[i].next);
        }
        length += calculateDistance(getAmountNodes() - 1, 0);
        return length;
    }



    /**
     * This method is used to calculate the amount of time needed to run the Algorithm
     *
     * @param startInstant needs the time when the Algorithm gets started
     * @return the amount of time needed for the Algorithm
     */

    public String getTime(Instant startInstant)
    {
        String totalTime;
        Duration permutationDuration = Duration.between(startInstant, Instant.now());
        long minutes = permutationDuration.toMinutes();
        long seconds = permutationDuration.getSeconds();
        if (seconds > 59)
        {
            long tempSeconds = seconds - 60 * minutes;
            long tempMilliseconds = permutationDuration.toMillis() - seconds * 1000;
            totalTime = ("Duration: " + minutes + " minutes " + tempSeconds + " seconds " +
                    tempMilliseconds + " milliseconds " + "(" + permutationDuration + ")");
        } else if (seconds > 0)
        {
            long tempMilliSeconds = permutationDuration.toMillis() - seconds * 1000;
            totalTime = ("Duration " + seconds + " seconds " + tempMilliSeconds + " milliseconds " +
                    "" + "(" + permutationDuration + ")");
        } else
        {
            totalTime = ("Duration: " + permutationDuration.toMillis() + " milliseconds (" +
                    permutationDuration + ")");
        }

        return totalTime;

    }



    /**
     * This method is used to paint the Graph
     *
     * @param pane    needs a pane to paint the Graph on
     * @param weights checks whether to draw the weights of the Edges or not
     * @param names   checks whether to draw the name of the Nodes or not
     */

    public void paintGraph(Pane pane, boolean weights, boolean names)
    {
        Circle firstCircle = new Circle(nodes[0].getXpos(), nodes[0].getYpos(), 10);
        firstCircle.setFill(Color.GREEN);
        pane.getChildren().add(firstCircle);
        for (int i = 1; i < getAmountNodes(); i++)
        {
            nodes[i].paintNodes(pane);
            if (names == true)
            {
                nodes[i].paintNodeNames(pane);
            }
        }

        for (int i = 0; i < getAmountEdges(); i++)
        {
            if (edges[i] == null)
            {
                break;
            } else
            {
                edges[i].paintEdges(pane);
                if (weights == true)
                {
                    edges[i].paintWeights(pane);
                }
            }
        }
    }



    /**
     * Gets the amount of Nodes.
     *
     * @return the amount of NOdes
     */
    public int getAmountNodes()
    {
        return nodes.length;
    }



    /**
     * Gets the amount of Edges.
     *
     * @return the amount of Edges
     */
    public int getAmountEdges()
    {
        return edges.length;
    }



    /**
     * This Method gets the searching edge.
     *
     * @param i is the edge that we are searching in the edge array.
     * @return return the edge.
     */
    public Edges returnEdge(int i)
    {
        if (i < 0 || i >= getAmountEdges())
        {
            return null;
        }

        return edges[i];
    }



    /**
     * This method get the searching node.
     *
     * @param i is the node, that we are searching in node array.
     * @return the node.
     */
    public Nodes returnNode(int i)
    {
        if (i < 0 || i >= getAmountNodes())
        {
            return null;
        }
        return nodes[i];
    }



    /**
     * This Method creates a Graph to use for Testing
     */

    public void createGivenGraphForTesting()
    {
        nodes[0] = new Nodes(0, "A", 50, 150);
        nodes[1] = new Nodes(1, "E", 150, 280);
        nodes[2] = new Nodes(2, "F", 300, 280);
        nodes[3] = new Nodes(3, "D", 450, 150);
        nodes[4] = new Nodes(4, "B", 150, 50);
        nodes[5] = new Nodes(5, "C", 300, 50);

        edges[0] = new Edges(nodes[0], nodes[1], 5);
        edges[1] = new Edges(nodes[1], nodes[2], 2);
        edges[2] = new Edges(nodes[2], nodes[3], 2);
        edges[3] = new Edges(nodes[3], nodes[5], 4);
        edges[4] = new Edges(nodes[5], nodes[4], 7);
        edges[5] = new Edges(nodes[4], nodes[0], 7);

    }

}
