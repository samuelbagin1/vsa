/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banka;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author edu
 */
@Entity
@Table(name="UCET")
public class Ucet {
    
    @Id
    @Column(name="ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="VLASTNIK_ID")
    private Osoba vlastnik;
    
    @Column(name="STAV")
    private Double stav;
    
    @OneToMany(mappedBy="z_id")
    private List<Prevod> odoslanePrevody;
    
    @OneToMany(mappedBy="na_id")
    private List<Prevod> prijatePrevody;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Osoba getVlastnik() {
        return vlastnik;
    }

    public void setVlastnik(Osoba vlastnik) {
        this.vlastnik = vlastnik;
    }

    public Double getStav() {
        return stav;
    }

    public void setStav(Double stav) {
        this.stav = stav;
    }

    public List<Prevod> getOdoslanePrevody() {
        return odoslanePrevody;
    }

    public void setOdoslanePrevody(List<Prevod> odoslanePrevody) {
        this.odoslanePrevody = odoslanePrevody;
    }

    public List<Prevod> getPrijatePrevody() {
        return prijatePrevody;
    }

    public void setPrijatePrevody(List<Prevod> prijatePrevody) {
        this.prijatePrevody = prijatePrevody;
    }
    
    
    
    
}
