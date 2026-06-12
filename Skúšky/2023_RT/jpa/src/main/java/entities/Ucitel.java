/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author edu
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Ucitel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String meno;
    @ManyToMany(mappedBy = "cviciaci")
    private List<Predmet> cvicenie;

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

    public List<Predmet> getCvicenie() {
        return cvicenie;
    }

    public void setCvicenie(List<Predmet> cvicenie) {
        this.cvicenie = cvicenie;
    }
     
}
