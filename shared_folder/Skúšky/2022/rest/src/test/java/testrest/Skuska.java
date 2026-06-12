package testrest;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Skuska {
    private String predmet;
    private String den;
    private List<String> student;

    public Skuska() {
        student = new ArrayList<>();
    }

    public Skuska(String predmet, String den) {
        this.predmet = predmet;
        this.den = den;
        this.student = new ArrayList<>();
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public List<String> getStudent() {
        return student;
    }

    public void setStudent(List<String> student) {
        this.student = student;
    }
}
