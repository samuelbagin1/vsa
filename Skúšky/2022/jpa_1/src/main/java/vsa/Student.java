/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student extends Osoba {
    
    @Column(name = "ROCNIK")
    private String rocnik;
    
    @ManyToMany(mappedBy = "studenti")
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
