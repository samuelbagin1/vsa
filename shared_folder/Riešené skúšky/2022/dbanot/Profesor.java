package vsa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PROFESOR")
public class Profesor extends Osoba {
    @Column(name="USTAV")
    private String ustav;

    public String getUstav() {
        return ustav;
    }

    public void setUstav(String ustav) {
        this.ustav = ustav;
    }
    
    
    
}
