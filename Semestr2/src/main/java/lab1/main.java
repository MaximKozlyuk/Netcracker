package lab1;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class main {

    public static void main(String[] args) {
        String path = "/Users/max/IdeaProjects/Netcracker/Semestr2/src/main/java/lab1/group.xml";
        try {
            XmlParser parser = new XmlParser();
            List<Student> students = parser.parseStudents(new File(path));

            // check average mark
            float average = 0;
            Student currStudent;
            for (int i = 0; i < students.size(); i++) {
                currStudent = students.get(i);
                Set<String> subjs = currStudent.getMarksOfSubjects().keySet();
                Map<String, Integer> subjsMarks = currStudent.getMarksOfSubjects();
                for (String subj : subjs) {
                    average += subjsMarks.get(subj);
                }
                if (currStudent.getAverage() != (average / subjsMarks.size())) {
                    parser.correctAvgMark(currStudent, average / subjsMarks.size());
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

}
