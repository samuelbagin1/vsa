/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbapp;

import jakarta.persistence.*;

/**
 *
 * @author edu
 */
enum Dostupnost { NASKLADE, IHNED, DO5DNI }

@Entity
@Table
public class Kniha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String titul;
    @Enumerated(EnumType.STRING)
    private Dostupnost dostupnost;
    private Double cena;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitul() {
        return titul;
    }

    public void setTitul(String titul) {
        this.titul = titul;
    }

    public Dostupnost getDostupnost() {
        return dostupnost;
    }

    public void setDostupnost(Dostupnost dostupnost) {
        this.dostupnost = dostupnost;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kniha other = (Kniha) obj;
        return this.id == other.id;
    }

    
    
    
    
}
