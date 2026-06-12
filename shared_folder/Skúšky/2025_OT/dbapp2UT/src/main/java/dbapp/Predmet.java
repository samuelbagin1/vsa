package dbapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Predmet implements Serializable {
    @Id
    @Column(nullable = false, unique = true)
    private String kod;
    
    private String odbor;
    
    @ManyToMany(mappedBy = "predmety")
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

    public void setVyucujuci(Set<Ucitel> ucitelia) {
        this.vyucujuci = ucitelia;
    }

    
}
