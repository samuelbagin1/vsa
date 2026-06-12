package dbapp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;
@Entity
public class Predmet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String kod;                     // primarny kluc - kod predmetu
    private String odbor;  
    @ManyToMany(mappedBy="predmety")
    private Set<Ucitel> vyucujuci;          // osoby ktore ucia predmet

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kod != null ? kod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the kod fields are not set
        if (!(object instanceof Predmet)) {
            return false;
        }
        Predmet other = (Predmet) object;
        if ((this.kod == null && other.kod != null) || (this.kod != null && !this.kod.equals(other.kod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Predmet[ kod=" + kod + " ]";
    }

}
