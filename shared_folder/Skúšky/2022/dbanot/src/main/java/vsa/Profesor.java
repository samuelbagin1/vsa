package vsa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Profesor extends Osoba { 
    @Column(name = "USTAV")
    private String ustav;
    
    @OneToMany(mappedBy = "prednasajuci")
    private List<Predmet> prednasky;

    public String getUstav() {
        return ustav;
    }

    public void setUstav(String ustav) {
        this.ustav = ustav;
    }

    public <any> getPrednasky() {
        return prednasky;
    }

    public void setPrednasky(<any> prednasky) {
        this.prednasky = prednasky;
    }
    
    
}
