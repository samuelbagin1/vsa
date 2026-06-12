/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbapp;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author edu
 */
@Entity
public class Docent extends Ucitel {
    
    @OneToMany(mappedBy = "prednasajuci")
    private List<Predmet> prednaska;

    public List<Predmet> getPrednaska() {
        return prednaska;
    }

    public void setPrednaska(List<Predmet> prednaska) {
        this.prednaska = prednaska;
    }
    
    
    
}
