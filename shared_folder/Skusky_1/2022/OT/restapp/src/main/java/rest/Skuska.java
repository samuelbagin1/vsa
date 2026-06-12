package rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Gmod4
 */
@XmlRootElement(name = "skuska")
public class Skuska {

    private String predmet;
    private String den;
    private List<String> student = new ArrayList<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skuska zmluva = (Skuska) o;
        return predmet.equals(zmluva.predmet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predmet);
    }

}
