package skola;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

enum Odbor { MSUS, BIS , TK };

@Entity
public class Predmet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "KOD")
    private String kod;                     // primarny kluc - kod predmetu
    @Enumerated(EnumType.STRING)
    @Column(name = "ODBOR")
    private Odbor odbor;
    @ManyToMany(mappedBy = "predmety")
    private Set<Student> studenti;          // studenti, ktori maju predmet zapisany

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Set<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(Set<Student> studenti) {
        this.studenti = studenti;
    }

    public Odbor getOdbor() {
        return odbor;
    }

    public void setOdbor(Odbor odbor) {
        this.odbor = odbor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kod != null ? kod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the kod fields are not set
        if (!(object instanceof Predmet)) {
            return false;
        }
        Predmet other = (Predmet) object;
        if ((this.kod == null && other.kod != null) || (this.kod != null && !this.kod.equals(other.kod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Predmet[ kod=" + kod + " ]";
    }

}
