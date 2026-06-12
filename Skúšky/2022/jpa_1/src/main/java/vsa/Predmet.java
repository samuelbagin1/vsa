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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author edu
 */
@Entity
@Table(name = "PREDMET")
public class Predmet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NAZOV")
    private String nazov;
    
    @ElementCollection
    @CollectionTable(name = "PREDMET_LITERATURA", joinColumns = @JoinColumn(name = "PREDMET_ID"))
    @Column(name = "LITERATURA")
    private List<String> literatura;
    
    @ManyToOne
    @JoinColumn(name = "PREDNASAJUCI_ID")
    private Profesor prednasajuci;
    
    @ManyToMany
    @JoinTable(
        name = "STUDENT_PREDMET",
        joinColumns = @JoinColumn(name = "PREDMET_FK"),
        inverseJoinColumns = @JoinColumn(name = "STUDENT_FK"))   
    private List<Student> studenti;

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

    public List<String> getLiteratura() {
        return literatura;
    }

    public void setLiteratura(List<String> literatura) {
        this.literatura = literatura;
    }

    public Profesor getPrednasajuci() {
        return prednasajuci;
    }

    public void setPrednasajuci(Profesor prednasajuci) {
        this.prednasajuci = prednasajuci;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predmet)) {
            return false;
        }
        Predmet other = (Predmet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vsa.Predmet[ id=" + id + " ]";
    }
    
}
