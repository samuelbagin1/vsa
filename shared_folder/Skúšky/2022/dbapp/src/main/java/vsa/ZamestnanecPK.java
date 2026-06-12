package vsa;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ZamestnanecPK {   
    @Column(name = "MENO")
    private String meno;
    
    @Column(name = "DATUM_OD")
    private LocalDate datum_od;

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
    
    
    
}

