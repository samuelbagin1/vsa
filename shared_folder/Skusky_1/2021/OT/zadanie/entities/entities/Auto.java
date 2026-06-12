package entities;

import java.io.Serializable;
import java.util.Date;

public class Auto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String spz;

    private String znacka;

    private Date stk;                       // datum platnosti STK

    public Date getStk() {
        return stk;
    }

    public void setStk(Date stk) {
        this.stk = stk;
    }
    private Osoba majitel;                  // osoba, ktorej auto patri 

    public Osoba getMajitel() {
        return majitel;
    }

    public void setMajitel(Osoba majitel) {
        this.majitel = majitel;
    }
   public String getZnacka() {
        return znacka;
    }

    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }
    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

}
