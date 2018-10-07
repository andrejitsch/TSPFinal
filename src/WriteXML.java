import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Project TravellingSalesmanProblem This class is used to write the Graph with its Nodes and Edges
 * into an XML-File
 *
 * @Author Andrej Drobin
 * @Author Deniz Kücüktas
 * @Author Julian Geerdes
 * @Date 28.05.2018
 * @Version 1.1 Last Change: 28.09.2018
 */

public class WriteXML
{

    /**
     * This method is used to create the XML-File
     *
     * @param salesmanProblem needs a given Graph
     * @param directoryPath   needs the directoryPath where to save the file
     */
    public void createXML(TravellingSalesmanProblem salesmanProblem, String directoryPath)
    {

        try
        {
            DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbF.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("Nodes");
            doc.appendChild(rootElement);
            Element nodes = doc.createElement("Node");
            rootElement.appendChild(nodes);

            for (int i = 0; i < salesmanProblem.getAmountNodes(); i++)
            {
                Nodes n1 = salesmanProblem.returnNode(i);
                System.out.println(i + ". Nodes = " + n1);
                n1.generateXML(doc, nodes);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
            Date now = new Date();
            String strDate = sdfDate.format(now);
            StreamResult result =
                    new StreamResult(new File(directoryPath + "/graph" + strDate + ".xml"));
            transformer.transform(source, result);
        } catch (Exception var12)
        {
            var12.printStackTrace();
        }
    }

}
