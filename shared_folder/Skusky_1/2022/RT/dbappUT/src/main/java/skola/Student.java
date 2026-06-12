package skola;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;                        // primarny kluc - autogenerovane id
    @Column(name = "MENO")
    private String meno;
    @Temporal(TemporalType.DATE)
    @Column(name = "NARODENY")
    private Date narodeny;
    @ManyToMany
    @JoinTable(name = "STUDENT_PREDMET",
    joinColumns = @JoinColumn(name = "STUDENT_ID"),
    inverseJoinColumns = @JoinColumn(name = "PREDMET_KOD"))
    private Set<Predmet> predmety;         // predmety, ktore ma student zapisane

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

    public Student() {
        this.predmety = new HashSet<>();
    }

    public Student(String meno) {
        this.meno = meno;
        this.predmety = new HashSet<>();
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student[ id=" + id + " ]";
    }
}
