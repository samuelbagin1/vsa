/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author edu
 */
@XmlRootElement
public class Poistenie {
    private String idZmluvy;
    private Osoba majitel;
    @XmlTransient
    private ArrayList<Osoba> poistenci = new ArrayList<>();
    private int pocetPoistencov; 


    public String getIdZmluvy() {
        return idZmluvy;
    }

    public void setIdZmluvy(String idZmluvy) {
        this.idZmluvy = idZmluvy;
    }

    public Osoba getMajitel() {
        return majitel;
    }

    public void setMajitel(Osoba majitel) {
        this.majitel = majitel;
    }
    @XmlTransient
    public ArrayList<Osoba> getPoistenci() {
        return poistenci;
    }

    public void setPoistenci(ArrayList<Osoba> poistenci) {
        this.poistenci = poistenci;
    }

    public int getPocetPoistencov() {
        return pocetPoistencov;
    }

    public void setPocetPoistencov(int pocetPoistencov) {
        this.pocetPoistencov = pocetPoistencov;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idZmluvy);
        hash = 17 * hash + Objects.hashCode(this.majitel);
        hash = 17 * hash + Objects.hashCode(this.poistenci);
        hash = 17 * hash + this.pocetPoistencov;
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
        final Poistenie other = (Poistenie) obj;
        if (this.pocetPoistencov != other.pocetPoistencov) {
            return false;
        }
        if (!Objects.equals(this.idZmluvy, other.idZmluvy)) {
            return false;
        }
        if (!Objects.equals(this.majitel, other.majitel)) {
            return false;
        }
        if (!Objects.equals(this.poistenci, other.poistenci)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Poistenie{" + "idZmluvy=" + idZmluvy + ", majitel=" + majitel + ", poistenci=" + poistenci + ", pocetPoistencov=" + pocetPoistencov + '}';
    }


    
}
