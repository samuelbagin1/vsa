/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbapp;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author igor
 */
public class Osoba implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;                    // primarny kluc - autogenerovany
    private String meno;
        
    private List<Predmet> prednasky;    // predmety ktore prednasa

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

    public List<Predmet> getPrednasky() {
        return prednasky;
    }

    public void setPrednasky(List<Predmet> prednasky) {
        this.prednasky = prednasky;
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
        if (!(object instanceof Osoba)) {
            return false;
        }
        Osoba other = (Osoba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Osoba[ id=" + id + " ]";
    }
    
}
