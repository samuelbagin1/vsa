package proxy;


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
@XmlRootElement(name="poistenie")
public class Poistenie {
    
    private String idZmluvy;

    private Osoba majitel;
    
    private int pocet;

    @XmlElement(name="pocetPoistencov")
    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

//    private List<String> poistenaOsoba;

    public Osoba getMajitel() {
        return majitel;
    }

    public void setMajitel(Osoba majitel) {
        this.majitel = majitel;
    }

//    public List<String> getPoistenaOsoba() {
//        return poistenaOsoba;
//    }

    public Poistenie() {
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
