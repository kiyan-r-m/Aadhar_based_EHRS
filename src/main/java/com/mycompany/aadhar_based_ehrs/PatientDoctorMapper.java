/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aadhar_based_ehrs;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "patient_doctor_mapper", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "PatientDoctorMapper.findAll", query = "SELECT p FROM PatientDoctorMapper p"),
    @NamedQuery(name = "PatientDoctorMapper.findByPdid", query = "SELECT p FROM PatientDoctorMapper p WHERE p.pdid = :pdid"),
    @NamedQuery(name = "PatientDoctorMapper.findByPatientid", query = "SELECT p FROM PatientDoctorMapper p WHERE p.patientid = :patientid"),
    @NamedQuery(name = "PatientDoctorMapper.findByDoctorid", query = "SELECT p FROM PatientDoctorMapper p WHERE p.doctorid = :doctorid"),
    @NamedQuery(name = "PatientDoctorMapper.findByDiseaseid", query = "SELECT p FROM PatientDoctorMapper p WHERE p.diseaseid = :diseaseid"),
    @NamedQuery(name = "PatientDoctorMapper.findByFromdate", query = "SELECT p FROM PatientDoctorMapper p WHERE p.fromdate = :fromdate"),
    @NamedQuery(name = "PatientDoctorMapper.findByTodate", query = "SELECT p FROM PatientDoctorMapper p WHERE p.todate = :todate")})
public class PatientDoctorMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pdid")
    private Integer pdid;
    @Column(name = "patientid")
    private Integer patientid;
    @Column(name = "doctorid")
    private Integer doctorid;
    @Column(name = "diseaseid")
    private Integer diseaseid;
    @Column(name = "fromdate")
    @Temporal(TemporalType.DATE)
    private Date fromdate;
    @Column(name = "todate")
    @Temporal(TemporalType.DATE)
    private Date todate;

    public PatientDoctorMapper() {
    }

    public PatientDoctorMapper(Integer pdid) {
        this.pdid = pdid;
    }

    public Integer getPdid() {
        return pdid;
    }

    public void setPdid(Integer pdid) {
        this.pdid = pdid;
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

    public Integer getDiseaseid() {
        return diseaseid;
    }

    public void setDiseaseid(Integer diseaseid) {
        this.diseaseid = diseaseid;
    }

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdid != null ? pdid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientDoctorMapper)) {
            return false;
        }
        PatientDoctorMapper other = (PatientDoctorMapper) object;
        if ((this.pdid == null && other.pdid != null) || (this.pdid != null && !this.pdid.equals(other.pdid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.PatientDoctorMapper[ pdid=" + pdid + " ]";
    }
    
}
