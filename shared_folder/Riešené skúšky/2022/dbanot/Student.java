/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student extends Osoba {
    @Column(name="ROCNIK", nullable=false)
    private String rocnik;
    
    @ManyToMany
    @JoinTable(name="STUDENT_PREDMET",
            joinColumns=@JoinColumn(name = "STUDENT_FK"),
            inverseJoinColumns=@JoinColumn(name = "PREDMET_FK"))
    private List<Predmet> predmety;

    public String getRocnik() {
        return rocnik;
    }

    public void setRocnik(String rocnik) {
        this.rocnik = rocnik;
    }

    public List<Predmet> getPredmety() {
        return predmety;
    }

    public void setPredmety(List<Predmet> predmety) {
        this.predmety = predmety;
    }
    
    
}
