package testrest;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Poistenie {
    
    private String idZmluvy;

    private double poistnaSuma;

    public double getPoistnaSuma() {
        return poistnaSuma;
    }

    public void setPoistnaSuma(double poistnaSuma) {
        this.poistnaSuma = poistnaSuma;
    }

    private Osoba vlastnik;
    
    private int pocetPoistencov;

    public int getPocetPoistencov() {
        return pocetPoistencov;
    }

    public void setPocetPoistencov(int pocetPoistencov) {
        this.pocetPoistencov = pocetPoistencov;
    }

    private List<Osoba> poistenaOsoba;

    public Osoba getVlastnik() {
        return vlastnik;
    }

    public void setVlastnik(Osoba vlastnik) {
        this.vlastnik = vlastnik;
    }

    @XmlTransient
    public List<Osoba> getPoistenaOsoba() {
        return poistenaOsoba;
    }

    public Poistenie() {
        poistenaOsoba = new ArrayList<>();
        
    }

    public void setPoistenaOsoba(List<Osoba> poistenaOsoba) {
        this.poistenaOsoba = poistenaOsoba;
    }

    public String getIdZmluvy() {
        return idZmluvy;
    }

    public void setIdZmluvy(String idZmluvy) {
        this.idZmluvy = idZmluvy;
    }

}
