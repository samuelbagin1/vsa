package banka;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="UCET")
public class Ucet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name="VLASTNIK")
    private Osoba vlastnik; 
    private double stav;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Osoba getVlastnik() {
        return vlastnik;
    }

    public void setVlastnik(Osoba vlastnik) {
        this.vlastnik = vlastnik;
    }

    public double getStav() {
        return stav;
    }

    public void setStav(double stav) {
        this.stav = stav;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.vlastnik);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.stav) ^ (Double.doubleToLongBits(this.stav) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ucet other = (Ucet) obj;
        if (Double.doubleToLongBits(this.stav) != Double.doubleToLongBits(other.stav)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.vlastnik, other.vlastnik)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ucet{" + "id=" + id + ", vlastnik=" + vlastnik + ", stav=" + stav + '}';
    }
    
}

 