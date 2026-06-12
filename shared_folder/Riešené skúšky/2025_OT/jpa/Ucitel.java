package dbapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="UCITEL")
public class Ucitel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(name="MENO", nullable=false)
    private String meno;
    
    @Temporal(TemporalType.DATE)
    @Column(name="NARODENY")
    private Date narodeny;
    
    @ManyToMany
    @JoinTable(name="UCITEL_PREDMET",
            joinColumns=@JoinColumn(name="UCITEL_ID"),
            inverseJoinColumns=@JoinColumn(name="PREDMET_KOD"))
    private Set<Predmet> predmety;

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

    public Date getNarodeny() {
        return narodeny;
    }

    public void setNarodeny(Date narodeny) {
        this.narodeny = narodeny;
    }

    public Set<Predmet> getPredmety() {
        return predmety;
    }

    public void setPredmety(Set<Predmet> predmety) {
        this.predmety = predmety;
    }
    
    
    
    

}
