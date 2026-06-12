package vsa;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROFESOR")
public class Profesor extends Osoba {
    
    @Column(name = "USTAV")
    private String ustav;
    
    @OneToMany(mappedBy = "prednasajuci")
    private List<Predmet> predmety;

    public String getUstav() {
        return ustav;
    }

    public void setUstav(String ustav) {
        this.ustav = ustav;
    }

    public List<Predmet> getPredmety() {
        return predmety;
    }

    public void setPredmety(List<Predmet> predmety) {
        this.predmety = predmety;
    }
    
    
}
