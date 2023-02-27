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
@Table(name = "patient_access_mapper", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "PatientAccessMapper.findAll", query = "SELECT p FROM PatientAccessMapper p"),
    @NamedQuery(name = "PatientAccessMapper.findByAccessid", query = "SELECT p FROM PatientAccessMapper p WHERE p.accessid = :accessid"),
    @NamedQuery(name = "PatientAccessMapper.findByPatientid", query = "SELECT p FROM PatientAccessMapper p WHERE p.patientid = :patientid"),
    @NamedQuery(name = "PatientAccessMapper.findByDoctorid", query = "SELECT p FROM PatientAccessMapper p WHERE p.doctorid = :doctorid"),
    @NamedQuery(name = "PatientAccessMapper.findByIsactive", query = "SELECT p FROM PatientAccessMapper p WHERE p.isactive = :isactive")})
public class PatientAccessMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accessid")
    private Integer accessid;
    @Column(name = "patientid")
    private Integer patientid;
    @Column(name = "doctorid")
    private Integer doctorid;
    @Column(name = "isactive")
    private Boolean isactive;

    public PatientAccessMapper() {
    }

    public PatientAccessMapper(Integer accessid) {
        this.accessid = accessid;
    }

    public Integer getAccessid() {
        return accessid;
    }

    public void setAccessid(Integer accessid) {
        this.accessid = accessid;
    }

    public Integer getPatientid() {
        return patientid;
    }

    public void setPatientid(Integer patientid) {
        this.patientid = patientid;
    }

    public Integer getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accessid != null ? accessid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientAccessMapper)) {
            return false;
        }
        PatientAccessMapper other = (PatientAccessMapper) object;
        if ((this.accessid == null && other.accessid != null) || (this.accessid != null && !this.accessid.equals(other.accessid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.PatientAccessMapper[ accessid=" + accessid + " ]";
    }
    
}
