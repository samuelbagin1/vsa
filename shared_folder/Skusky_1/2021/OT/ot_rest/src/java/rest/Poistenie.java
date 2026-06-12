/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Micha
 */
@XmlRootElement(name = "poistenie")
public class Poistenie {
    
    String idZmluvy;
    String majitel;
    ArrayList<String> poistenci = new ArrayList<>(); 

    public String getIdZmluvy() {
        return idZmluvy;
    }

    public void setIdZmluvy(String idZmluvy) {
        this.idZmluvy = idZmluvy;
    }

    public String getMajitel() {
        return majitel;
    }

    public void setMajitel(String majitel) {
        this.majitel = majitel;
    }

    @XmlElement(name = "poistenaOsoba")
    public ArrayList<String> getPoistenci() {
        return poistenci;
    }

    public void setPoistenci(ArrayList<String> poistenci) {
        this.poistenci = poistenci;
    }
    
}
