/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banka;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ubuntu
 */
@Entity
@Table(name = "PREVOD")
public class Prevod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CAS_PREVODU")
    private Date cas_prevodu;
    
    @Column(name = "SUMA", nullable = false)
    private Double suma;
    
    @ManyToOne
    @JoinColumn(name = "NA_ID", nullable = false)
    private Ucet na_id;
    
    @ManyToOne
    @JoinColumn(name = "Z_ID", nullable = false)
    private Ucet z_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCas_prevodu() {
        return cas_prevodu;
    }

    public void setCas_prevodu(Date cas_prevodu) {
        this.cas_prevodu = cas_prevodu;
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }

    public Ucet getNa_id() {
        return na_id;
    }

    public void setNa_id(Ucet na_id) {
        this.na_id = na_id;
    }

    public Ucet getZ_id() {
        return z_id;
    }

    public void setZ_id(Ucet z_id) {
        this.z_id = z_id;
    }

    
    
}
