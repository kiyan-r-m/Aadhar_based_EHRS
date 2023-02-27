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
@Table(name = "symptoms", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Symptoms.findAll", query = "SELECT s FROM Symptoms s"),
    @NamedQuery(name = "Symptoms.findBySymptomid", query = "SELECT s FROM Symptoms s WHERE s.symptomid = :symptomid"),
    @NamedQuery(name = "Symptoms.findBySymptomname", query = "SELECT s FROM Symptoms s WHERE s.symptomname = :symptomname")})
public class Symptoms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "symptomid")
    private Integer symptomid;
    @Size(max = 50)
    @Column(name = "symptomname")
    private String symptomname;

    public Symptoms() {
    }

    public Symptoms(Integer symptomid) {
        this.symptomid = symptomid;
    }

    public Integer getSymptomid() {
        return symptomid;
    }

    public void setSymptomid(Integer symptomid) {
        this.symptomid = symptomid;
    }

    public String getSymptomname() {
        return symptomname;
    }

    public void setSymptomname(String symptomname) {
        this.symptomname = symptomname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (symptomid != null ? symptomid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Symptoms)) {
            return false;
        }
        Symptoms other = (Symptoms) object;
        if ((this.symptomid == null && other.symptomid != null) || (this.symptomid != null && !this.symptomid.equals(other.symptomid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.Symptoms[ symptomid=" + symptomid + " ]";
    }
    
}
