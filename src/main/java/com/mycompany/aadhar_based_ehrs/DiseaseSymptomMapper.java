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
@Table(name = "disease_symptom_mapper", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "DiseaseSymptomMapper.findAll", query = "SELECT d FROM DiseaseSymptomMapper d"),
    @NamedQuery(name = "DiseaseSymptomMapper.findByDsid", query = "SELECT d FROM DiseaseSymptomMapper d WHERE d.dsid = :dsid"),
    @NamedQuery(name = "DiseaseSymptomMapper.findByDiseaseid", query = "SELECT d FROM DiseaseSymptomMapper d WHERE d.diseaseid = :diseaseid"),
    @NamedQuery(name = "DiseaseSymptomMapper.findBySymptomid", query = "SELECT d FROM DiseaseSymptomMapper d WHERE d.symptomid = :symptomid")})
public class DiseaseSymptomMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dsid")
    private Integer dsid;
    @Column(name = "diseaseid")
    private Integer diseaseid;
    @Column(name = "symptomid")
    private Integer symptomid;

    public DiseaseSymptomMapper() {
    }

    public DiseaseSymptomMapper(Integer dsid) {
        this.dsid = dsid;
    }

    public Integer getDsid() {
        return dsid;
    }

    public void setDsid(Integer dsid) {
        this.dsid = dsid;
    }

    public Integer getDiseaseid() {
        return diseaseid;
    }

    public void setDiseaseid(Integer diseaseid) {
        this.diseaseid = diseaseid;
    }

    public Integer getSymptomid() {
        return symptomid;
    }

    public void setSymptomid(Integer symptomid) {
        this.symptomid = symptomid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dsid != null ? dsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiseaseSymptomMapper)) {
            return false;
        }
        DiseaseSymptomMapper other = (DiseaseSymptomMapper) object;
        if ((this.dsid == null && other.dsid != null) || (this.dsid != null && !this.dsid.equals(other.dsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.DiseaseSymptomMapper[ dsid=" + dsid + " ]";
    }
    
}
