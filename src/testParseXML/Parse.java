package testParseXML;

import org.w3c.dom.*;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import testParseXML.model.Group;
import testParseXML.model.Student;
import testParseXML.model.Subject;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Parse {


    public static void main(String[] args) {

        try {
            Group group = parse("src/testParseXML/group.xml");
            group.changeAllAverageMark();

            File output = new File("src/testParseXML/newGroup.xml");


            DocumentBuilderFactory documentBuilderFactoryOutPut = DocumentBuilderFactory.newInstance();
            DocumentBuilder outPutBuilder = documentBuilderFactoryOutPut.newDocumentBuilder();
            Document outPutDocument = outPutBuilder.newDocument();
            Element root = outPutDocument.createElement("group");
            outPutDocument.appendChild(root);

            for (Student st: group.getStudentList()) {
                Element student = outPutDocument.createElement("student");
                root.appendChild(student);
                student.setAttribute("firstname",st.getFirstName());
                student.setAttribute("lastname",st.getLastName());
                student.setAttribute("groupnumber",st.getGroupNumber());


                for (Subject sub: st.getSubjectList()) {
                    Element subject = outPutDocument.createElement("subject");
                    subject.setAttribute("title",sub.getTitle());
                    subject.setAttribute("mark",Integer.toString(sub.getMark()));
                    student.appendChild(subject);
                }

                Element average = outPutDocument.createElement("average");
                student.appendChild(average);
                Text textAverage = outPutDocument.createTextNode(Double.toString(st.getAverageMark()));
                average.appendChild(textAverage);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(new DOMSource(outPutDocument),new StreamResult(new FileOutputStream(output)));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public static Group parse(String fileName) throws IOException, SAXException, ParserConfigurationException {

        Group group = new Group();
        File inputFile = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                exception.printStackTrace();
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                exception.printStackTrace();
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                exception.printStackTrace();
            }
        });
        Document document = documentBuilder.parse(inputFile);

        NodeList nodeList = document.getElementsByTagName("student");
        for (int i = 0; i < nodeList.getLength() ; i++) {
            Node node = nodeList.item(i);
            Student student = new Student();

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;
                student.setFirstName(element.getAttribute("firstname"));
                student.setLastName(element.getAttribute("lastname"));
                student.setGroupNumber(element.getAttribute("groupnumber"));

                NodeList subjects = element.getElementsByTagName("subject");
                for (int j = 0; j < subjects.getLength(); j++) {
                    NamedNodeMap attributes = element.getElementsByTagName("subject").item(j).getAttributes();

                    Subject subject = new Subject();

                    subject.setTitle(attributes.getNamedItem("title").getTextContent());
                    subject.setMark(Integer.parseInt(attributes.getNamedItem("mark").getTextContent()));

                    student.addSubject(subject);

                }
                student.setAverageMark(Double.parseDouble(element.getElementsByTagName("average").item(0).getTextContent()));
            }
            group.addStudentToGroup(student);
        }

        return group;
    }
}
