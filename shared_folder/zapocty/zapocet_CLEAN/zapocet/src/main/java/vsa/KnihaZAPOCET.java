package vsa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Table;

@Entity
@Table(name = "KNIHA")
public class KnihaZAPOCET implements Serializable {

    @Id
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "NAZOV", nullable = true)
    private String nazov;

    @Column(name = "CENA", nullable = true)
    private Double cena;

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getNazov() { return nazov; }
    public void setNazov(String nazov) { this.nazov = nazov; }

    public Double getCena() { return cena; }
    public void setCena(Double cena) { this.cena = cena; }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final KnihaZAPOCET other = (KnihaZAPOCET) obj;
        return Objects.equals(this.isbn, other.isbn);
    }

    @Override
    public String toString() {
        return "KnihaZAPOCET{isbn=" + isbn + ", nazov=" + nazov + ", cena=" + cena + '}';
    }
}
