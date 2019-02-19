package lab1;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class main {

    public static void main(String[] args) {
        String path = "/Users/max/IdeaProjects/Netcracker/Semestr2/src/main/java/lab1/group.xml";
        try {
            XmlParser parser = new XmlParser();
            List<Student> students = parser.parseStudents(new File(path));
            for (Student i : students) {
                System.out.println(i + "\n");
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

}
