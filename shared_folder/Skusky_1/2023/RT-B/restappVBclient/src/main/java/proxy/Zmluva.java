package proxy;


import proxy.Osoba;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author igor
 */
@XmlRootElement
public class Zmluva {
    
    private String idZmluvy;

    private Osoba vlastnik;
    
    private int pocet;

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

//    private List<String> poistenaOsoba;

    public Osoba getVlastnik() {
        return vlastnik;
    }

    public void setVlastnik(Osoba vlastnik) {
        this.vlastnik = vlastnik;
    }

//    public List<String> getPoistenaOsoba() {
//        return poistenaOsoba;
//    }

    public Zmluva() {
//        poistenaOsoba = new ArrayList<>();
        
    }

//    public void setPoistenaOsoba(List<String> poistenaOsoba) {
//        this.poistenaOsoba = poistenaOsoba;
//    }

    public String getIdZmluvy() {
        return idZmluvy;
    }

    public void setIdZmluvy(String idZmluvy) {
        this.idZmluvy = idZmluvy;
    }

}
