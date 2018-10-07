import javafx.scene.layout.Pane;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by AS on 03.10.2018.
 */

public class TravellingSalesmanProblem
{
    Nodes[] nodes;
    Edges[] edges;



    /**
     * This is the constructor of the class TravellingSalesmanProblem.
     * The constructor multiplies the nodes array, and take this size for the edges array.
     * @param nodesLength needs the amount of nodes.
     *
     */
    public TravellingSalesmanProblem(int nodesLength)
    {
        this.nodes = new Nodes[nodesLength];
        this.edges = new Edges[nodesLength * nodesLength];
    }

    /**
     *This is the Random-engine.
     * This method create nodes and edges.
     * the variables of nodes for example x and y are random.
     * the name get from the loop.
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
        /*for (int i=0; i<getAmountEdges(); i++)
        {
            int s = r.nextInt(getAmountNodes());
            int t = r.nextInt(getAmountNodes());

            int counter = 0;

            // if source and target are equal
            while (s == t && counter < 1000)
            {
                t = r.nextInt(getAmountNodes());
                counter++;
            }

            //not equal
            if (s != t)
            {
                Edges newEdges = new Edges(nodes[s], nodes[t], getWeight(calculateDistance(s, t)));
                edges[i] = newEdges;

                if (nodes[s].edges.contains(newEdges))
                {
                    i--;

                    continue;
                }else {
                    nodes[s].edges.add(newEdges);
                    nodes[t].edges.add(newEdges);

                    System.out.println(newEdges);

                }
            }*/

    }

    public void convertNodes(ArrayList<Nodes> nodesA)
    {

        for (int i = 0; i < nodes.length; i++)
        {
            nodes[i] = new Nodes(nodesA.get(i).getNumber(), nodesA.get(i).getName(),
                    nodesA.get(i).getXpos(), nodesA.get(i).getYpos());
        }

    }

    public void createEdges() {
        int x = 0;
        int d = 1;
        for (int i = 0; i<nodes.length-1; i++){
            for (int j=d; j<nodes.length; j++){
                edges[x]= new Edges(nodes[i], nodes[j]);
                x++;
            }
            d++;
        }
    }


    /**
     * Get the weight of the Edges.
     * @return the weight of the edge.
     */
    public int getWeight(double cost)
    {
        int index = 0;

        if (cost>=0 && cost<50)
        {
            index = 1;
        }
        if (cost>=50 && cost<100)
        {
            index = 2;
        }

        if (cost>=100 && cost<150)
        {
            index = 3;
        }

        if (cost>=150 && cost<200)
        {
            index = 4;
        }
        if (cost>=200 && cost<250)
        {
            index = 5;
        }
        if (cost>=250 && cost<300)
        {
            index = 6;
        }
        if (cost>=300 && cost<350)
        {
            index = 7;
        }
        if (cost>=350 && cost<400)
        {
            index = 8;
        }
        if (cost>=400 && cost<450)
        {
            index = 9;
        }
        if (cost>=450 && cost<500)
        {
            index = 10;
        }
        if (cost>=500 && cost<550)
        {
            index=11;
        }
        if (cost>=550 && cost<600)
        {
            index=12;
        }
        if (cost>=600 && cost<650)
        {
            index=13;
        }
        if (cost>=650 && cost<700)
        {
            index=14;
        }
        if (cost>=700 && cost<750)
        {
            index=15;
        }
        if (cost>=750 && cost<800)
        {
            index=16;
        }
        if (cost>=800)
        {
            index=17;
        }

        return index;
    }

    /**
     * This method calculates the distance between two points.
     * @param n1 needs a number for example of a node.
     * @param n2 needs a number for example of a node.
     * @return the distance between this nodes.
     */
    public double calculateDistance(int n1, int n2)
    {
        double dx,dy,dx2,dy2;

        dx = nodes[n1].getXpos() - nodes[n2].getXpos();
        dy = nodes[n1].getYpos() - nodes[n2].getYpos();
        dx2 = dx * dx;
        dy2 = dy * dy;

        return Math.sqrt(dx2 + dy2);
    }

    /**
     * This method calculates the closest node.
     * @param n searching of the closest neighbour of n.
     * @return the closest neighbour that is not yet in the tour.
     */
    private int nextNode(int n)
    {
        double dist, min = 10000;
        int index = -1;

        int start = 0;

        while (start<getAmountNodes())
        {
            while ((start<getAmountNodes()) && nodes[start].getInTour()) start++;

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
     * @param s needs the number of the node.
     * @param n needs the number of the node.
     * @return true  if exists else false.
     */
    private boolean controlEdges(int s, int n)
    {
        Edges controlEdges = new Edges(nodes[s], nodes[n]);
        Edges controlEdges2 = new Edges(nodes[n], nodes[s]);

        boolean success = false;

        for (int i=0; i<getAmountEdges(); i++)
        {

            if (controlEdges.equals(edges[i]) || controlEdges2.equals(edges[i]))
            {
                success = true;
                break;
            }
            else {
                success = false;
            }
        }

        return success;
    }

    /**
     * This method saves the next Node from the Method nextNode.
     * The boolean value of inTour will be set on true, for the visited nodes.
     * The first node is always zero(default setting).
     * The last node connect with the first node.
     */
    public void nearestNeighbour()
    {
        int s = 0;

        for (int i=0; i<getAmountNodes()-1; i++)
        {
            nodes[s].inTour = true;
            nodes[s].next = nextNode(s);
            s = nodes[s].next;
            System.out.println(s);
        }
        nodes[s].inTour = true;
        nodes[s].next = 0;
    }

    public void paintGraph(Pane pane)
    {
        for (int i=0; i<getAmountNodes(); i++)
        {
            nodes[i].paintNodes(pane);
        }

        for (int i=0; i<getAmountEdges(); i++)
        {
            if (edges[i]==null)
            {
                break;
            }else {
                edges[i].paintEdges(pane);
            }
        }
    }

    /**
     * Gets the amount of Nodes.
     * @return the nodesLenght value.
     */
    public int getAmountNodes()
    {
        return nodes.length;
    }

    /**
     * Gets the amount of Edges.
     * @return the edgesLenght value.
     */
    public int getAmountEdges()
    {
        return edges.length;
    }

    /**
     * This Method get the searching edge.
     * @param i is the edge that we are searching in the edge array.
     * @return return the edge.
     */
    public Edges returnEdge(int i)
    {
        if (i<0 || i >= getAmountEdges())
        {
            return null;
        }

        return edges[i];
    }

    /**
     * This method get the searching node.
     * @param i is the node, that we are searching in node array.
     * @return the node.
     */
    public Nodes returnNode(int i)
    {
        if (i<0 || i >=getAmountNodes())
        {
            return  null;
        }
        return nodes[i];
    }

    /**
     *
     * @return The total Distance of the Tour
     */

    public String allCost()
    {
        double allCosts = 0;
        double length = 0;
        for (int i = 0; i < getAmountNodes() - 1; i++)
        {
            length += calculateDistance(i, nodes[i].next);
        }
        length += calculateDistance(getAmountNodes() - 1, 0);
        allCosts = Math.round(length);
        return Double.toString(allCosts);
    }

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





}
