package lab1;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String firstName;
    private String lastname;
    private int groupNumber;
    private float average;
    private Map<String, Integer> marksOfSubjects;

    public Student() { }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public Map<String, Integer> getMarksOfSubjects() {
        return marksOfSubjects;
    }

    public void setMarksOfSubjects(HashMap<String, Integer> marksOfSubjects) {
        this.marksOfSubjects = marksOfSubjects;
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
                ",\nmarksOfSubjects=" + marksOfSubjects +
                '}';
    }
}
