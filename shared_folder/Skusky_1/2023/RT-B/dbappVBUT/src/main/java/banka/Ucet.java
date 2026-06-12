package banka;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="UCET")
public class Ucet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name="MAJITEL")
    private Osoba majitel;     
    private double stav;

    public Osoba getMajitel() {
        return majitel;
    }

    public void setMajitel(Osoba majitel) {
        this.majitel = majitel;
    }

    public double getStav() {
        return stav;
    }

    public void setStav(double stav) {
        this.stav = stav;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ucet)) {
            return false;
        }
        Ucet other = (Ucet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ucet[ id=" + id + " ]";
    }
    
}
