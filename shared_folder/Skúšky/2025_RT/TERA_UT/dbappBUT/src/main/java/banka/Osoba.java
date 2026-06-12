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
@Table(name = "OSOBA")
public class Osoba implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "MENO")
    private String meno;
    
    @Column(name = "BYDLISKO")
    private String bydlisko;
    
    @OneToMany(mappedBy = "majitel")
    private List<Ucet> ucty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getBydlisko() {
        return bydlisko;
    }

    public void setBydlisko(String bydlisko) {
        this.bydlisko = bydlisko;
    }

    public List<Ucet> getUcty() {
        return ucty;
    }

    public void setUcty(List<Ucet> ucty) {
        this.ucty = ucty;
    }
    
    
}
