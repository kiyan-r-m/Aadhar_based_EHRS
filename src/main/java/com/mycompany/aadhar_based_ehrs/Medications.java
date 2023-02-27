/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aadhar_based_ehrs;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "medications", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Medications.findAll", query = "SELECT m FROM Medications m"),
    @NamedQuery(name = "Medications.findByMedicationid", query = "SELECT m FROM Medications m WHERE m.medicationid = :medicationid"),
    @NamedQuery(name = "Medications.findByMedication", query = "SELECT m FROM Medications m WHERE m.medication = :medication")})
public class Medications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "medicationid")
    private Integer medicationid;
    @Size(max = 50)
    @Column(name = "medication")
    private String medication;

    public Medications() {
    }

    public Medications(Integer medicationid) {
        this.medicationid = medicationid;
    }

    public Integer getMedicationid() {
        return medicationid;
    }

    public void setMedicationid(Integer medicationid) {
        this.medicationid = medicationid;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicationid != null ? medicationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medications)) {
            return false;
        }
        Medications other = (Medications) object;
        if ((this.medicationid == null && other.medicationid != null) || (this.medicationid != null && !this.medicationid.equals(other.medicationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.Medications[ medicationid=" + medicationid + " ]";
    }
    
}
