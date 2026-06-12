/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edu
 */
@XmlRootElement
public class Osoba {
    
    private String meno;
    private String rocnik;
    private String adresa;

    public Osoba() {
    }

    public Osoba(String meno, String rc, String adresa) {
        this.meno = meno;
        this.rocnik = rc;
        this.adresa = adresa;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getRocnik() {
        return rocnik;
    }

    public void setRocnik(String rocnik) {
        this.rocnik = rocnik;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

}
