/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rest;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edu
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Poistenie {
    @XmlElement(required = true)
    private String idZmluvy;
    private Double poistnaSuma;
    @XmlElement(name = "vlastnik")
    private Osoba vlastnik;
    private int pocetPoistencov;
    @XmlTransient
    private List<Osoba> osoby = new ArrayList<>();

    public Poistenie() {
        osoby = new ArrayList<>();
        pocetPoistencov = osoby.size();
    }
    
   

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

    public Osoba getVlastnik() {
        return vlastnik;
    }

    public void setVlastnik(Osoba vlastnik) {
        this.vlastnik = vlastnik;
    }

    public int getPocetPoistencov() {
        return pocetPoistencov;
    }

    public void setPocetPoistencov(int pocetPoistencov) {
        this.pocetPoistencov = pocetPoistencov;
    }

    public List<Osoba> getOsoby() {
        return osoby;
    }

    public void setOsoby(List<Osoba> osoby) {
        this.osoby = osoby;
    }
    
    
    
}
