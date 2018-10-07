import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by AS on 07.10.2018.
 */
public class ReadXML
{

    ArrayList<Nodes> nodes = new ArrayList<Nodes>();


    /**
     * Creates an ArrayList of the Nodes out of the XML-File
     * @return the ArrayList of Nodes
     */
    public ArrayList<Nodes> readNodes(File inputFile)
    {
        try
        {


            DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbF.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Node");
            ArrayList<Nodes> nodes = new ArrayList<>();
            for (int i=0; i<nList.getLength(); i ++)
            {
                Node nNode = nList.item(i);
                Element element = (Element) nNode;
                String name = readXML(element, "Name");
                String xPosString = readXML(element, "X");
                Integer xPos = Integer.parseInt(xPosString);
                Integer yPos = Integer.parseInt(readXML(element, "Y"));
                Nodes n = new Nodes(i, name + i, xPos, yPos);
                nodes.add(n);
            }

            //Output
            System.out.println("Amount of Nodes: " + nodes.size());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return nodes;
    }

    /**
     * This method get the value of the searching Element.
     * @param element needs an element.
     * @param tagName needs the tagName.
     * @return the value in the tagName.
     */
    private static String readXML(Element element, String tagName)
    {
        String tagValue = element.getElementsByTagName(tagName)
                .item(0)
                .getTextContent();
        System.out.print(" " + tagValue);
        return tagValue;
    }





}

