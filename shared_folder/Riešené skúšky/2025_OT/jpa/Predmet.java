package dbapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name="PREDMET")
public class Predmet implements Serializable {
    @Id
    @Column(name="KOD")
    private String kod;
    
    @Column(name="ODBOR")
    private String odbor;
    
    @ManyToMany(mappedBy="predmety")
    private Set<Ucitel> vyucujuci;

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getOdbor() {
        return odbor;
    }

    public void setOdbor(String odbor) {
        this.odbor = odbor;
    }

    public Set<Ucitel> getVyucujuci() {
        return vyucujuci;
    }

    public void setVyucujuci(Set<Ucitel> vyucujuci) {
        this.vyucujuci = vyucujuci;
    }
    
    
}
