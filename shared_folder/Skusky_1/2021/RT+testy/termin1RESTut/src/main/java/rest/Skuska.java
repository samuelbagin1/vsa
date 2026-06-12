/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gmod4
 */
@XmlRootElement(name = "skuska")
public class Skuska {
    
//    @XmlElement(required=true)
    String predmet;
    
    String termin;
    
    ArrayList<String> studenti = new ArrayList<>();

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }

    @XmlElement(name = "student")
    public ArrayList<String> getStudenti() {
        return studenti;
    }

    public void setStudenti(ArrayList<String> studenti) {
        this.studenti = studenti;
    }
    
    
    
}
