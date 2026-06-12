/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author edu
 */
@Entity
@Table(name="PREDMET")
public class Predmet {
    @Id
    @Column(name="NAZOV")
    private String nazov;
    
    @ManyToMany
    @JoinTable(name="PREDMET_UCITEL",
            joinColumns=@JoinColumn(name="CVICENIE_NAZOV"),
            inverseJoinColumns=@JoinColumn(name="CVICIACI_ID"))
    private List<Ucitel> ucitelia;
    
    
    @ManyToOne
    @JoinColumn(name="PREDNASAJUCI_ID")
    private Docent prednasajuci_id;

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public List<Ucitel> getUcitelia() {
        return ucitelia;
    }

    public void setUcitelia(List<Ucitel> ucitelia) {
        this.ucitelia = ucitelia;
    }

    public Docent getPrednasajuci_id() {
        return prednasajuci_id;
    }

    public void setPrednasajuci_id(Docent prednasajuci_id) {
        this.prednasajuci_id = prednasajuci_id;
    }
    
    
}
