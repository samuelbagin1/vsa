/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author edu
 */
@XmlRootElement
public class Zmluva {
    private String idZmluvy;
    private Osoba vlastnik;
    @XmlTransient
    private List<Osoba> poistenci = new ArrayList<>();
    private int pocet;


    @XmlTransient
    public List<Osoba> getPoistenci() {
        return poistenci;
    }

    public void setPoistenci(List<Osoba> poistenci) {
        this.poistenci = poistenci;
    }

    
    
    
    
    public String getIdZmluvy() {
        return idZmluvy;
    }

    public void setIdZmluvy(String idZmluvy) {
        this.idZmluvy = idZmluvy;
    }

    public Osoba getVlastnik() {
        return vlastnik;
    }

    public void setVlastnik(Osoba vlastnik) {
        this.vlastnik = vlastnik;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idZmluvy);
        hash = 23 * hash + Objects.hashCode(this.vlastnik);
        hash = 23 * hash + Objects.hashCode(this.poistenci);
        hash = 23 * hash + this.pocet;
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
        final Zmluva other = (Zmluva) obj;
        if (this.pocet != other.pocet) {
            return false;
        }
        if (!Objects.equals(this.idZmluvy, other.idZmluvy)) {
            return false;
        }
        if (!Objects.equals(this.vlastnik, other.vlastnik)) {
            return false;
        }
        if (!Objects.equals(this.poistenci, other.poistenci)) {
            return false;
        }
        return true;
    }
    
    
    
}
