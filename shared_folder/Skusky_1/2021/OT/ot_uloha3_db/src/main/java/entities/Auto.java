package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_AUTO")
public class Auto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="SPZ")
    private String spz;

    @Column(name="ZNACKA")
    private String znacka;

    @Column(name="STK_DO")
    private Date stk;                       // datum platnosti STK

    public Date getStk() {
        return stk;
    }

    public void setStk(Date stk) {
        this.stk = stk;
    }
    
    @Column(name="MAJITEL_FK")
    @ManyToOne
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
