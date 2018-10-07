import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXML{
    public WriteXML(){
    }



    /**
     * Create the XML-File.
     */
    public void createXML(TravellingSalesmanProblem salesmanProblem, String directoryPath)
    {

        try{
            DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbF.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("Nodes");
            doc.appendChild(rootElement);
            Element nodes = doc.createElement("Node");
            rootElement.appendChild(nodes);

            for (int i=0; i<salesmanProblem.getAmountNodes(); i++)
            {
                Nodes n1 = salesmanProblem.returnNode(i);
                System.out.println(i + ". Nodes = " + n1);
                n1.generateXML(doc, nodes);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(directoryPath + "graph.xml"));
            transformer.transform(source,result);
        }catch (Exception var12){
            var12.printStackTrace();
        }
    }


}
