package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;

@Entity
@SecondaryTable(name="T_OSOBA")
public class Adresa implements Serializable {

    @EmbeddedId
    @Column(table= "T_OSOBA", name="OBEC")
    private String obec; 
    
    @Column(table= "T_OSOBA", name="ULICA")
    private String ulica;

    @Column(table= "T_OSOBA", name="PSC")
    private String psc; 

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }
    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getObec() {
        return obec;
    }

    public void setObec(String obec) {
        this.obec = obec;
    }
    
}
