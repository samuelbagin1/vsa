/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banka;

import jakarta.persistence.Column;
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
@Table(name="OSOBA")
public class Osoba {
    @Id
    @Column(name="ID")
    private Long id;
    
    @Column(name="MENO")
    private String meno;
    
    @Column(name="ADRESA")
    private String adresa;
    
    @OneToMany(mappedBy="vlastnik")
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Ucet> getUcty() {
        return ucty;
    }

    public void setUcty(List<Ucet> ucty) {
        this.ucty = ucty;
    }
    
    
}
