package lab1;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class XmlParser {

    private Document doc;

    public List<Student> parseStudents (File file) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Student> studentsList = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(file);
        NodeList studentsNodes = doc.getDocumentElement().getElementsByTagName("student");

        Student student;
        Node currentNode;
        NodeList subjectsNodes;
        for (int i = 0; i < studentsNodes.getLength(); i++) {
            student = new Student();
            currentNode = studentsNodes.item(i);

            // parsing basic student info
            student.setFirstName(
                    currentNode.getAttributes().removeNamedItem("firstname").getTextContent());

            student.setLastname(
                    currentNode.getAttributes().removeNamedItem("lastname").getTextContent());

            student.setGroupNumber(
                    Integer.parseInt(
                            currentNode.getAttributes().removeNamedItem("groupnumber").getTextContent()));

            // parsing subjects
            HashMap<String, Integer> subjects = new HashMap<>();
            subjectsNodes = ((Element) currentNode).getElementsByTagName("subject");
            NamedNodeMap subjAtrs;
            for (int subj = 0; subj < subjectsNodes.getLength(); subj++) {
                subjAtrs = subjectsNodes.item(subj).getAttributes();
                subjects.put(
                        subjAtrs.getNamedItem("title").getTextContent(),
                        Integer.parseInt(subjAtrs.getNamedItem("mark").getTextContent())
                );
            }
            //student.setMarksOfSubjects(subjects);

            //getting average mark
            NodeList average = ((Element) currentNode).getElementsByTagName("average");
            if (average.getLength() != 0) {
                student.setAverage(
                        Float.parseFloat(average.item(0).getTextContent()));
            }

            studentsList.add(student);
        }

        return studentsList;
    }

    public Group jaxbUnmarshalling(File f) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Group.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Group) jaxbUnmarshaller.unmarshal(f);
    }

    public void jaxbMarshalling (Group group) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Group.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(group, System.out);
        //jaxbMarshaller.marshal(group, new File("c:/temp/employees.xml"));
    }

}
