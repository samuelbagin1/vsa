/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banka;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author edu
 */
@Entity
@Table(name="PREVOD")
public class Prevod {
    
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="Z_ID", nullable=false)
    private Ucet z_id;
    
    @ManyToOne
    @JoinColumn(name="NA_ID", nullable=false)
    private Ucet na_id;
    
    @Column(name="SUMA", nullable=false)
    private Double suma;
    
    @Column(name="REALIZOVANY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date realizovany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ucet getZ_id() {
        return z_id;
    }

    public void setZ_id(Ucet z_id) {
        this.z_id = z_id;
    }

    public Ucet getNa_id() {
        return na_id;
    }

    public void setNa_id(Ucet na_id) {
        this.na_id = na_id;
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }

    public Date getRealizovany() {
        return realizovany;
    }

    public void setRealizovany(Date realizovany) {
        this.realizovany = realizovany;
    }
    
    
    
}
