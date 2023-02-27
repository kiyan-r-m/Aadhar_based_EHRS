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
@Table(name = "patient_allergy_mapper", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "PatientAllergyMapper.findAll", query = "SELECT p FROM PatientAllergyMapper p"),
    @NamedQuery(name = "PatientAllergyMapper.findByPaid", query = "SELECT p FROM PatientAllergyMapper p WHERE p.paid = :paid"),
    @NamedQuery(name = "PatientAllergyMapper.findByUserid", query = "SELECT p FROM PatientAllergyMapper p WHERE p.userid = :userid"),
    @NamedQuery(name = "PatientAllergyMapper.findByAllergyid", query = "SELECT p FROM PatientAllergyMapper p WHERE p.allergyid = :allergyid")})
public class PatientAllergyMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "paid")
    private Integer paid;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "allergyid")
    private Integer allergyid;

    public PatientAllergyMapper() {
    }

    public PatientAllergyMapper(Integer paid) {
        this.paid = paid;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAllergyid() {
        return allergyid;
    }

    public void setAllergyid(Integer allergyid) {
        this.allergyid = allergyid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paid != null ? paid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientAllergyMapper)) {
            return false;
        }
        PatientAllergyMapper other = (PatientAllergyMapper) object;
        if ((this.paid == null && other.paid != null) || (this.paid != null && !this.paid.equals(other.paid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.PatientAllergyMapper[ paid=" + paid + " ]";
    }
    
}
