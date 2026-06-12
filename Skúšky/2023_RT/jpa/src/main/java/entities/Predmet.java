/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;

/**
 *
 * @author edu
 */
@Entity
public class Predmet {
    @Id
    private String nazov;
    @ManyToOne
    @JoinColumn(name = "prednasajuci_id", nullable = false)
    private Docent prednasajuci;
    @ManyToMany
    private List<Ucitel> cviciaci;
    

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public Docent getPrednasajuci() {
        return prednasajuci;
    }

    public void setPrednasajuci(Docent prednasajuci) {
        this.prednasajuci = prednasajuci;
    }

    public List<Ucitel> getCviciaci() {
        return cviciaci;
    }

    public void setCviciaci(List<Ucitel> cviciaci) {
        this.cviciaci = cviciaci;
    }
    
}
