package lab1;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute(name = "firstname")
    private String firstName;
    @XmlAttribute(name = "lastname")
    private String lastname;
    @XmlAttribute(name = "groupnumber")
    private int groupNumber;

    @XmlElement
    private float average;

    @XmlElement(name = "subject")
    private Set<Subject> subjectSet;

    public Student() { }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    public void setSubjectSet(Set<Subject> subjectSet) {
        this.subjectSet = subjectSet;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", groupNumber=" + groupNumber +
                ", average=" + average +
                ", subjectSet=" + subjectSet +
                '}';
    }
}
