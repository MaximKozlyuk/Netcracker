package lab1;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Set;

public class main {

    public static void main(String[] args) {
        String path = "/Users/max/IdeaProjects/Netcracker/Semestr2/src/main/java/lab1/group.xml";
        XmlParser parser = new XmlParser();
        Group group;
        try {
            group = parser.jaxbUnmarshalling(new File(path));
            float avg = 0;
            Set<Subject> subjs;
            for (Student s : group.getStudents()) {
                subjs = s.getSubjectSet();
                for (Subject subj : subjs) {
                    avg += subj.getMark();
                }
                avg /= subjs.size();
                if (s.getAverage() != 0 && s.getAverage() != avg) {
                    s.setAverage(avg);
                }
                avg = 0;
            }
            parser.jaxbMarshalling(group);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void domMarshalingCode () {
//        try {
//            XmlParser parser = new XmlParser();
//            List<Student> students = parser.parseStudents(new File(path));
//
//            // check average mark
//            float average = 0;
//            Student currStudent;
//            for (int i = 0; i < students.size(); i++) {
//                currStudent = students.get(i);
//                Set<String> subjs = currStudent.getMarksOfSubjects().keySet();
//                Map<String, Integer> subjsMarks = currStudent.getMarksOfSubjects();
//                for (String subj : subjs) {
//                    average += subjsMarks.get(subj);
//                }
//                if (currStudent.getAverage() != (average / subjsMarks.size())) {
//                    parser.correctAvgMark(currStudent, average / subjsMarks.size());
//                }
//            }
//
//        } catch (ParserConfigurationException | IOException | SAXException e) {
//            e.printStackTrace();
//        }
    }

}
