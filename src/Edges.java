import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by AS on 02.10.2018.
 */
public class Edges extends Line
{
    TravellingSalesmanProblem salesmanProblem;
    Nodes sourceNode = null;
    Nodes targetNode = null;
    int weight;



    /**
     * This is the constructor of the class Edges.
     * @param sN needs a sourceNode from Nodes.
     * @param tN needs a targetNode from Nodes.
     */
    public Edges(Nodes sN, Nodes tN)
    {
        this.sourceNode = sN;
        this.targetNode = tN;
        this.weight = getWeight();
    }

    /**
     * This method is need to generate the xml structure with their tags.
     * Structure would be: <Edge><Source></Source><Target><Target><W></W></Edge>
     * @param doc needs the Document to put the Elements in the doc.
     * @param edges need a Element to create an Element in the doc.
     */
    public void generateXML(Document doc, Element edges)
    {
        Element edge = doc.createElement("Edge");
        edges.appendChild(edge);
        Element source = doc.createElement("Source");
        source.setTextContent(getSourceNode().getName());
        edge.appendChild(source);
        Element target = doc.createElement("Target");
        target.setTextContent(getTargetNode().getName());
        edge.appendChild(target);
    }

    public void paintEdges(Pane pane)
    {
        Line line = new Line(getSourceNode().getXpos(), getSourceNode().getYpos(), getTargetNode(). getXpos(), getTargetNode().getYpos());
        pane.getChildren().add(line);
        line.toBack();
    }

    /**
     * This method returns the weight of the Edges.
     * @return the weight of the edge.
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Sets the sourceNode to the edge.
     * @param sN set the sourceNode.
     */
    public void setSourceNode(Nodes sN)
    {
        this.sourceNode = sN;
    }

    /**
     * Get the sourceNode of the edge.
     * @return sourceNode-
     */
    public Nodes getSourceNode()
    {
        return sourceNode;
    }

    /**
     * Set the targetNode of the Edge.
     * @param tN set a node as a targetNode of the edge.
     */
    public void setTargetNode(Nodes tN)
    {
        this.targetNode = tN;
    }

    /**
     * Gets the targetNode of the edge.
     * @return the targetNode.
     */
    public Nodes getTargetNode()
    {
        return targetNode;
    }


    /**
     * Set a weight to the Edges.
     * @param w need a number.
     */
    public void setWeight(int w)
    {
        this.weight = w;
    }

    /**
     * ToString method for the Edges.
     * @return sourceNode -> targetNode, weight
     */
    @Override
    public String toString()
    {
        return getSourceNode().toString() + " ------> " + getTargetNode().toString() + ", Weight: " + weight ;
    }

    /**
     * This method is needed to solve the problem with duplicates of Edges.
     * The method looks for example 1--->2,2---->1 etc
     * @param o of the class of Object.
     * @return duplicates as boolean.
     */
    @Override
    public boolean equals(Object o)
    {
        Edges edges = (Edges) o;

        return (getSourceNode().equals(edges.getSourceNode()) && getTargetNode().equals(edges.getTargetNode())||
                getSourceNode().equals(edges.getTargetNode()) && getTargetNode().equals(edges.getSourceNode()));
    }

    /**
     * Calculates the HashCode for Edges:
     * @return a hash code value of for the object:
     */
    @Override
    public int hashCode()
    {
        return getSourceNode().hashCode() + getTargetNode().hashCode();
    }

}
