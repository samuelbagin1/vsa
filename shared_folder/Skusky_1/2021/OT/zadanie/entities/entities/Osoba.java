package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Osoba implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String meno;                

    private Date narodena;                  // datum Narodenia

    private Adresa bydlisko;                // adresa

    private int vek;                        // aktualny vek osoby. Pozor! nie je v DB
    
    private List<Auto> zoznamAut;           // zoznam aut patriacich osobe

    public List<Auto> getZoznamAut() {
        return zoznamAut;
    }

    public void setZoznamAut(List<Auto> zoznamAut) {
        this.zoznamAut = zoznamAut;
    }

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }
    public Adresa getBydlisko() {
        return bydlisko;
    }

    public void setBydlisko(Adresa bydlisko) {
        this.bydlisko = bydlisko;
    }
    public Date getNarodena() {
        return narodena;
    }

    public void setNarodena(Date narodena) {
        this.narodena = narodena;
    }
    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
