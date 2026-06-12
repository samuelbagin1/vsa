/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbapp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import java.io.Serializable;

/**
 *
 * @author edu
 */
@Entity
public class Predmet {
    @Id
    private String nazov;
    
    @ManyToMany
    @JoinTable(
            name = "PREDMET_UCITEL",
            joinColumns = @JoinColumn(name = "CVICENIE_NAZOV"),
            inverseJoinColumns = @JoinColumn(name = "CVICIACI_ID")
    )
    private List<Ucitel> cviciaci;
    
    @ManyToOne
    @JoinColumn(name = "PREDNASAJUCI_ID")
    private Docent prednasajuci;

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public List<Ucitel> getCviciaci() {
        return cviciaci;
    }

    public void setCviciaci(List<Ucitel> cviciaci) {
        this.cviciaci = cviciaci;
    }

    public Docent getPrednasajuci() {
        return prednasajuci;
    }

    public void setPrednasajuci(Docent prednasajuci) {
        this.prednasajuci = prednasajuci;
    }
    
    
}
