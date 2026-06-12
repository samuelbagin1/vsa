package rest;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "zmluva")
public class Zmluva {
    private String id;
    private String majitel;
    private List<String> poistenec = new ArrayList<>();

    public Zmluva() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMajitel() {
        return majitel;
    }

    public void setMajitel(String majitel) {
        this.majitel = majitel;
    }

    public List<String> getPoistenec() {
        return poistenec;
    }

    public void setPoistenec(List<String> poistenec) {
        this.poistenec = poistenec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zmluva zmluva = (Zmluva) o;
        return id.equals(zmluva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Zmluva{" +
                "id=" + id +
                ", majitel='" + majitel + '\'' +
                ", poistenecList=" + poistenec +
                '}';
    }
}
