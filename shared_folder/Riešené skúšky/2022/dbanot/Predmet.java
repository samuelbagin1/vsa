/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author edu
 */

@Entity
@Table(name="PREDMET")
public class Predmet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(name="NAZOV", unique=true)
    private String nazov;
    
    @ManyToOne
    @JoinColumn(name="PREDNASAJUCI_ID")
    private Profesor prednasajuci_id;
    
    @ManyToMany(mappedBy="predmety")
    private List<Student> studenti;
    
    @ElementCollection
    @CollectionTable(name="PREDMET_LITERATURA",
            joinColumns=@JoinColumn(name="PREDMET_ID"))
    @Column(name="LITERATURA")
    private List<String> literatura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public Profesor getPrednasajuci_id() {
        return prednasajuci_id;
    }

    public void setPrednasajuci_id(Profesor prednasajuci_id) {
        this.prednasajuci_id = prednasajuci_id;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public List<String> getLiteratura() {
        return literatura;
    }

    public void setLiteratura(List<String> literatura) {
        this.literatura = literatura;
    }
    
    
}
