package banka;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OSOBA")

public class Osoba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String meno;
    private String adresa;
    @OneToMany(mappedBy="vlastnik")
    private List<Ucet> ucty;


    public Long getId() {
            return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Ucet> getUcty() {
        return ucty;
    }

    public void setUcty(List<Ucet> ucty) {
        this.ucty = ucty;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.meno);
        hash = 37 * hash + Objects.hashCode(this.adresa);
        hash = 37 * hash + Objects.hashCode(this.ucty);
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
        final Osoba other = (Osoba) obj;
        if (!Objects.equals(this.meno, other.meno)) {
            return false;
        }
        if (!Objects.equals(this.adresa, other.adresa)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ucty, other.ucty)) {
            return false;
        }
        return true;
    }
}
