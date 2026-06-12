/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Skuska {
    
    private String predmet;
    
    private String den;
    
    private List<String> studenti;

    @XmlElement
    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    @XmlElement
    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    @XmlElement(name = "student")
    public List<String> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<String> studenti) {
        this.studenti = studenti;
    }
    
    
}