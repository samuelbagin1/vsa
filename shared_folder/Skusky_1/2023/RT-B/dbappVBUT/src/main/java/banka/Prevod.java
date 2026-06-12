package banka;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PREVOD")
public class Prevod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double suma;
    @ManyToOne
    @JoinColumn(name="Z_ID",nullable = false)
    private Ucet ucetZ;     
    @ManyToOne
    @JoinColumn(name="NA_ID",nullable = false)
    private Ucet ucetNa;    
    @Temporal(TemporalType.TIMESTAMP)
    private Date zauctovany;

    public Prevod() {
    }

    public Prevod(double suma, Ucet zuctu, Ucet naucet) {
        this.suma = suma;
        this.ucetZ = zuctu;
        this.ucetNa = naucet;
    }

    public Ucet getUcetZ() {
        return ucetZ;
    }

    public void setUcetZ(Ucet ucetZ) {
        this.ucetZ = ucetZ;
    }

    public Ucet getUcetNa() {
        return ucetNa;
    }

    public void setUcetNa(Ucet ucetNa) {
        this.ucetNa = ucetNa;
    }

    public Date getZauctovany() {
        return zauctovany;
    }

    public void setZauctovany(Date zauctovany) {
        this.zauctovany = zauctovany;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
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
        if (!(object instanceof Prevod)) {
            return false;
        }
        Prevod other = (Prevod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Prevod[ id=" + id + " ]";
    }

}
