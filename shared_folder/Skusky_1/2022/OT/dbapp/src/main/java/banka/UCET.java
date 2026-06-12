package banka;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UCET implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    //@Column(unique = true)
    @Column(name = "STAV")
    private double stav;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getStav() {
        return stav;
    }

    public void setStav(double stav) {
        this.stav = stav;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }



    @Override
    public String toString() {
        return "Student[ id=" + id + " ]";
    }
}