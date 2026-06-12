package vsa;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ZamestnanecPK {
    @Column(name="MENO")
    private String meno;
    
    @Column(name="DATUM_OD")
    private LocalDate datum_od;
    
    public ZamestnanecPK() {
        
    }

    public ZamestnanecPK(String meno, LocalDate datum_od) {
        this.meno = meno;
        this.datum_od = datum_od;
    }
    
    

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public LocalDate getDatum_od() {
        return datum_od;
    }

    public void setDatum_od(LocalDate datum_od) {
        this.datum_od = datum_od;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.meno);
        hash = 89 * hash + Objects.hashCode(this.datum_od);
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
        final ZamestnanecPK other = (ZamestnanecPK) obj;
        if (!Objects.equals(this.meno, other.meno)) {
            return false;
        }
        return Objects.equals(this.datum_od, other.datum_od);
    }
    
    
    
    
    
}

