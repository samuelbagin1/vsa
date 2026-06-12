/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrest;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edu
 */
@XmlRootElement
public class Osoba {
    
    private String meno;
    private String rc;
    private String adresa;

    public Osoba() {
    }

    public Osoba(String meno, String rc, String adresa) {
        this.meno = meno;
        this.rc = rc;
        this.adresa = adresa;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

}
