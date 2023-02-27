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
@Table(name = "patient_chronic_mapper", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "PatientChronicMapper.findAll", query = "SELECT p FROM PatientChronicMapper p"),
    @NamedQuery(name = "PatientChronicMapper.findByPcid", query = "SELECT p FROM PatientChronicMapper p WHERE p.pcid = :pcid"),
    @NamedQuery(name = "PatientChronicMapper.findByUserid", query = "SELECT p FROM PatientChronicMapper p WHERE p.userid = :userid"),
    @NamedQuery(name = "PatientChronicMapper.findByDiseaseid", query = "SELECT p FROM PatientChronicMapper p WHERE p.diseaseid = :diseaseid")})
public class PatientChronicMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pcid")
    private Integer pcid;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "diseaseid")
    private Integer diseaseid;

    public PatientChronicMapper() {
    }

    public PatientChronicMapper(Integer pcid) {
        this.pcid = pcid;
    }

    public Integer getPcid() {
        return pcid;
    }

    public void setPcid(Integer pcid) {
        this.pcid = pcid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getDiseaseid() {
        return diseaseid;
    }

    public void setDiseaseid(Integer diseaseid) {
        this.diseaseid = diseaseid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pcid != null ? pcid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientChronicMapper)) {
            return false;
        }
        PatientChronicMapper other = (PatientChronicMapper) object;
        if ((this.pcid == null && other.pcid != null) || (this.pcid != null && !this.pcid.equals(other.pcid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.PatientChronicMapper[ pcid=" + pcid + " ]";
    }
    
}
