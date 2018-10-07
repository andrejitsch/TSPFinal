/**
 * Created by AS on 07.10.2018.
 */
public class NearestNeighbour
{
    TravellingSalesmanProblem salesmanProblem;

    public void  nearestNeighbour(int firstNode)
    {
        int s = firstNode;
        salesmanProblem.nodes[s].setFirstNode(true);

        for (int i=0; i<salesmanProblem.getAmountNodes()-1; i++)
        {
            salesmanProblem.nodes[s].setInTour(true);
            salesmanProblem.nodes[s].setNext(salesmanProblem.nextNode(s));
            s = salesmanProblem.nodes[s].getNext();
        }
        salesmanProblem.nodes[s].setInTour(true);
        salesmanProblem.nodes[s].setNext(firstNode);
    }

    public double allCost()
    {
        double lenght = 0;

        for (int s=0; s<salesmanProblem.getAmountNodes()-1; s++)
        {
            lenght += salesmanProblem.calculateDistance(s, salesmanProblem.nodes[s].getNext());
        }
        lenght += salesmanProblem.calculateDistance(salesmanProblem.getAmountNodes()-1, 0);
        return lenght;
    }

    public void shortestRoute(TravellingSalesmanProblem salesmanProblem)
    {
        nearestNeighbour(0);
        double min = allCost();

        int minIndex = 0;

        for (int i=1; i<salesmanProblem.getAmountNodes()-1; i++)
        {
            nearestNeighbour(i);
            if (allCost() < min)
            {
                min = allCost();
                minIndex = i;
            }

         nearestNeighbour(minIndex);

        }
    }
}
