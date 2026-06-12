package vsa;

import java.time.LocalDate;
import javax.persistence.Embeddable;

@Embeddable
public class ZamestnanecPK {
    
    private String meno;
    
    private LocalDate datumOd;

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }
    
    
}
