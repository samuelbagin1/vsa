package banka;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


  @Entity
@Table(name="PREVOD")
  @NamedQueries({
    // no parameters
    @NamedQuery(name = "Prevod.findAll", query = "SELECT p FROM Prevod p")})
public class Prevod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @JoinColumn(nullable = false)
    private double suma;
    @ManyToOne
    @JoinColumn(name="Z_ID",nullable = false)
    private Ucet ucetZ;     
    @ManyToOne
    @JoinColumn(name="NA_ID",nullable = false)
    private Ucet ucetNa;    
    @Temporal(TemporalType.TIMESTAMP)
    private Date realizovany;

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

    public Date getRealizovany() {
        return realizovany;
    }

    public void setRealizovany(Date realizovany) {
        this.realizovany = realizovany;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.suma) ^ (Double.doubleToLongBits(this.suma) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.ucetZ);
        hash = 41 * hash + Objects.hashCode(this.ucetNa);
        hash = 41 * hash + Objects.hashCode(this.realizovany);
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
        final Prevod other = (Prevod) obj;
        if (Double.doubleToLongBits(this.suma) != Double.doubleToLongBits(other.suma)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ucetZ, other.ucetZ)) {
            return false;
        }
        if (!Objects.equals(this.ucetNa, other.ucetNa)) {
            return false;
        }
        if (!Objects.equals(this.realizovany, other.realizovany)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prevod{" + "id=" + id + ", suma=" + suma + ", ucetZ=" + ucetZ + ", ucetNa=" + ucetNa + ", realizovany=" + realizovany + '}';
    }
    
    
}
