package vsa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Kniha {
    @Id
    private String nazov;
    private double cena;

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Kniha{" + "nazov=" + nazov + ", cena=" + cena + '}';
    }

}    

