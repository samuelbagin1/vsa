/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Zamestnanec {
    
    @EmbeddedId
    private ZamestnanecPK id;
    
    private double plat;

    public ZamestnanecPK getId() {
        return id;
    }

    public void setId(ZamestnanecPK id) {
        this.id = id;
    }

    public double getPlat() {
        return plat;
    }

    public void setPlat(double plat) {
        this.plat = plat;
    }
    
    
}
