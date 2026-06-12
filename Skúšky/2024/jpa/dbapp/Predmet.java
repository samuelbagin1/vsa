/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbapp;

import java.io.Serializable;


enum Odbor { TK, AI , ME };

/**
 *
 * @author igor
 */
public class Predmet implements Serializable {

    private static final long serialVersionUID = 1L;

    private String kod;           // primarny kluc - id predmetu

    private Odbor odbor;

    public Odbor getOdbor() {
        return odbor;
    }

    public void setOdbor(Odbor odbor) {
        this.odbor = odbor;
    }

    private Osoba profesor;             // osoba ktora prednasa predmet

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Osoba getProfesor() {
        return profesor;
    }

    public void setProfesor(Osoba profesor) {
        this.profesor = profesor;
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
        return "entities.Predmet[ id=" + kod + " ]";
    }

}
