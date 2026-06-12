/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banka;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ubuntu
 */
@Entity
@Table(name = "UCET")
public class Ucet implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MAJITEL_ID")
    private Osoba majitel;
    
    @Column(name = "STAV")
    private Double stav;
    
    @OneToMany(mappedBy = "z_id")
    private List<Prevod> z_id;
    
    @OneToMany(mappedBy = "na_id")
    private List<Prevod> na_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Osoba getMajitel() {
        return majitel;
    }

    public void setMajitel(Osoba majitel) {
        this.majitel = majitel;
    }

    public Double getStav() {
        return stav;
    }

    public void setStav(Double stav) {
        this.stav = stav;
    }

    public List<Prevod> getZ_id() {
        return z_id;
    }

    public void setZ_id(List<Prevod> z_id) {
        this.z_id = z_id;
    }

    public List<Prevod> getNa_id() {
        return na_id;
    }

    public void setNa_id(List<Prevod> na_id) {
        this.na_id = na_id;
    }


    
    
}
