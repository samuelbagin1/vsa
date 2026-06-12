/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rest;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ubuntu
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Poistenie {

    private String idZmluvy;
    
    private Double poistnaSuma;
    
    private Osoba majitel;
    private int pocetPoistencov;
    
    @XmlTransient
    private List<Osoba> poistenci;

    public String getIdZmluvy() {
        return idZmluvy;
    }

    public void setIdZmluvy(String idZmluvy) {
        this.idZmluvy = idZmluvy;
    }

    public Double getPoistnaSuma() {
        return poistnaSuma;
    }

    public void setPoistnaSuma(Double poistnaSuma) {
        this.poistnaSuma = poistnaSuma;
    }

    

    public Osoba getMajitel() {
        return majitel;
    }

    public void setMajitel(Osoba vlastnik) {
        this.majitel = vlastnik;
    }

    public int getPocetPoistencov() {
        return pocetPoistencov;
    }

    public void setPocetPoistencov(int pocetPoistencov) {
        this.pocetPoistencov = pocetPoistencov;
    }

    
    public List<Osoba> getPoistenci() {
        return poistenci;
    }

    public void setPoistenci(List<Osoba> poistenci) {
        this.poistenci = poistenci;
    }
    
    
}
