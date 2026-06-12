/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbapp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author edu
 */
@Entity
@Table(name="DOCENT")
public class Docent extends Ucitel {
    
    @OneToMany(mappedBy="prednasajuci_id")
    private List<Predmet> predmety;

    public List<Predmet> getPredmety() {
        return predmety;
    }

    public void setPredmety(List<Predmet> predmety) {
        this.predmety = predmety;
    }
    
    
}
