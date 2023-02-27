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

/**
 *
 * @author admin
 */
@Entity
@Table(name = "disease_medication_mapper", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "DiseaseMedicationMapper.findAll", query = "SELECT d FROM DiseaseMedicationMapper d"),
    @NamedQuery(name = "DiseaseMedicationMapper.findByDmid", query = "SELECT d FROM DiseaseMedicationMapper d WHERE d.dmid = :dmid"),
    @NamedQuery(name = "DiseaseMedicationMapper.findByDiseaseid", query = "SELECT d FROM DiseaseMedicationMapper d WHERE d.diseaseid = :diseaseid"),
    @NamedQuery(name = "DiseaseMedicationMapper.findByMedicationid", query = "SELECT d FROM DiseaseMedicationMapper d WHERE d.medicationid = :medicationid")})
public class DiseaseMedicationMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dmid")
    private Integer dmid;
    @Column(name = "diseaseid")
    private Integer diseaseid;
    @Column(name = "medicationid")
    private Integer medicationid;

    public DiseaseMedicationMapper() {
    }

    public DiseaseMedicationMapper(Integer dmid) {
        this.dmid = dmid;
    }

    public Integer getDmid() {
        return dmid;
    }

    public void setDmid(Integer dmid) {
        this.dmid = dmid;
    }

    public Integer getDiseaseid() {
        return diseaseid;
    }

    public void setDiseaseid(Integer diseaseid) {
        this.diseaseid = diseaseid;
    }

    public Integer getMedicationid() {
        return medicationid;
    }

    public void setMedicationid(Integer medicationid) {
        this.medicationid = medicationid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmid != null ? dmid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiseaseMedicationMapper)) {
            return false;
        }
        DiseaseMedicationMapper other = (DiseaseMedicationMapper) object;
        if ((this.dmid == null && other.dmid != null) || (this.dmid != null && !this.dmid.equals(other.dmid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.DiseaseMedicationMapper[ dmid=" + dmid + " ]";
    }
    
}
