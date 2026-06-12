/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ZAMESTNANEC")
public class Zamestnanec {
    @EmbeddedId
    private ZamestnanecPK id;
    
    @Column(name="PLAT")
    private Double plat;
    
    public Zamestnanec() {
        
    }

    public Zamestnanec(String meno, LocalDate datumOd, double plat) {
        this.id = new ZamestnanecPK(meno, datumOd);
        this.plat = plat;
    }
    
    

    public ZamestnanecPK getId() {
        return id;
    }

    public void setId(ZamestnanecPK id) {
        this.id = id;
    }

    public Double getPlat() {
        return plat;
    }

    public void setPlat(Double plat) {
        this.plat = plat;
    }
    
    
    
    
}
