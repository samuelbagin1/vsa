/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa2024;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

enum Odbor { TK, AI , ME };

/**
 *
 * @author ubuntu
 */
@Entity
@Table(name = "PREDMET")
public class Predmet implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private String kod;

    @Enumerated(EnumType.STRING)
    private Odbor odbor;
    
    @ManyToMany(mappedBy = "predmety")
    private List<Osoba> osoby;

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Odbor getOdbor() {
        return odbor;
    }

    public void setOdbor(Odbor odbor) {
        this.odbor = odbor;
    }

    public List<Osoba> getOsoby() {
        return osoby;
    }

    public void setOsoby(List<Osoba> osoby) {
        this.osoby = osoby;
    }
    
    
}
