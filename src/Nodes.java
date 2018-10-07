import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represented the nodes of the graph.
 */
public class Nodes extends Circle
{
    public int number;
    public String name;
    public int xPos;
    public int yPos;
    public int next;
    public boolean inTour;
    public boolean firstNode;


    /**
     * This is the Constructor of Nodes.
     * @param nb the node needs a number(int).
     * @param n the node needs a name(String).
     * @param x the node needs a x position(int).
     * @param y the node need a y position(int).
     */
    public Nodes(int nb, String n, int x, int y)
    {
        this.number = nb;
        this.name = n;
        this.xPos = x;
        this.yPos = y;

        next = 0;
        inTour = false;
        firstNode = false;
    }

    /**
     * This method is need to generate the xml structure with their tags.
     * Structure would be: <Nodes><Node><Name></Name><X></X><Y></Y></Node></Nodes>
     * @param doc needs the Document to put the Elements in the doc.
     * @param nodes need a Element to create an Element in the doc.
     */
    public void generateXML(Document doc, Element nodes)
    {
        Element node = doc.createElement("Node");
        nodes.appendChild(node);
        Element name = doc.createElement("Name");
        name.setTextContent(getName());
        node.appendChild(name);
        Element xPos = doc.createElement("X");
        xPos.setTextContent("" + getXpos());
        node.appendChild(xPos);
        Element yPos = doc.createElement("Y");
        yPos.setTextContent("" + getYpos());
        node.appendChild(yPos);
    }

    public void paintNodes(Pane pane)
    {
        Circle circle = new Circle(getXpos(),getYpos(),10);
        circle.setFill(Color.BLACK);
        pane.getChildren().add(circle);

    }

    public void paintNodeNames(Pane pane)
    {
        Text text = new Text(getXpos()-getName().length()*4, getYpos()+20, getName());
        pane.getChildren().add(text);
    }

    /**
     * This method calculates Distance in the Tours for the BruteForce.
     * @param node needs the next Node.
     * @return the distance between these two.
     */
    public double measureDistanceForBruteForce(Nodes node)
    {
        double deltaXpos = (node.getXpos() - this.getXpos());
        double deltaYpos = (node.getXpos() - this.getYpos());
        double dx2 = (deltaXpos * deltaXpos)/10;
        double dy2 = (deltaYpos * deltaYpos)/10;
        return Math.sqrt(dx2 + dy2);
    }

    /**
     * Sets the number for the node.
     * @param nb the number to set for the node.
     */
    public void setNumber(int nb)
    {
        this.number = nb;
    }

    /**
     * Gets the number for the node.
     * @return the number of the node.
     */
    public int getNumber()
    {
        return number;
    }

    /**
     * Sets the name for the node.
     * @param n the name to set the name.
     */
    public void setName(String n)
    {
        this.name = name;
    }

    /**
     * Gets the name of the node.
     * @return the name of the node.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the x position of the node.
     * @param x set the x position
     */
    public void setXpos(int x)
    {
        this.xPos = x;
    }

    /**
     * Gets the x position of the node.
     * @return get the x position.
     */
    public int getXpos()
    {
        return xPos;
    }

    /**
     * Sets the y position of the node.
     * @param y set the yPos.
     */
    public void setYpos(int y)
    {
        this.yPos = y;
    }

    /**
     * Gets the y position of the node.
     * @return get the y position.
     */
    public int getYpos()
    {
        return yPos;
    }

    /**
     * Set the inTour true or false.
     * @param iT set true or false for the route.
     */
    public void setInTour(boolean iT)
    {
        this.inTour = iT;
    }

    /**
     * Gets the value of inTour.
     * @return true or false of inTour.
     */
    public boolean getInTour()
    {
        return inTour;
    }

    /**
     * Sets the node as firstNode.
     * @param fN set the firstNode true or false;
     */
    public void setFirstNode(boolean fN)
    {
        this.firstNode = fN;
    }

    /**
     * Gets the state of firstNode
     * @return the true or false for firstNode
     */
    public boolean getFirstNode()
    {
        return firstNode;
    }

    /**
     * Sets the next Node for the node.
     * @param n set the number of the next node.
     */
    public void setNext(int n)
    {
        this.next = n;
    }

    /**
     * Gets the next node of the node.
     * @return the next node number.
     */
    public int getNext()
    {
        return next;
    }

    /**
     * ToString Method for the class Nodes.
     * @return Node0: X: Y:
     */
    @Override
    public String toString()
    {
        return "Node" + getNumber() +":   X: " + getXpos() + ", Y: " + getYpos();
    }
}
