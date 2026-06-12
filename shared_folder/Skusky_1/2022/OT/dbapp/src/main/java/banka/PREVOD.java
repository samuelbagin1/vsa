package banka;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PREVOD implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //autogenerovany kluc id - primarny
    @Column(name = "ID")
    private Long id;
    //@Column(unique = true)
    @Column(name = "SUMA")
    private double suma;

    @ManyToOne
    @JoinColumn(name = "ZUCTU_ID")
    private UCET zuctu_id;

    @ManyToOne
    @JoinColumn(name = "NAUCET_ID")
    private UCET naucet_id;

    @Temporal(TemporalType.DATE)
    @Column(name = "REALIZOVANY")
    private Date realizovany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public double getSuma() {
        return suma;
    }

    public UCET getZuctu_id() {
        return zuctu_id;
    }

    public void setZuctu_id(UCET zuctu_id) {
        this.zuctu_id = zuctu_id;
    }

    public UCET getNaucet_id() {
        return naucet_id;
    }

    public void setNaucet_id(UCET naucet_id) {
        this.naucet_id = naucet_id;
    }

    public Date getRealizovany() {
        return realizovany;
    }

    public void setRealizovany(Date realizovany) {
        this.realizovany = realizovany;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }



    @Override
    public String toString() {
        return "Student[ id=" + id + " ]";
    }
}
