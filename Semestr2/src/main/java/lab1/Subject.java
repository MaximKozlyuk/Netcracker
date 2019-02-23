package lab1;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {

    @XmlAttribute
    private String title;
    @XmlAttribute
    private float mark;

    public Subject() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
}
