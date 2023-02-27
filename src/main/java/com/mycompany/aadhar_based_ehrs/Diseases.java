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
@Table(name = "diseases", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Diseases.findAll", query = "SELECT d FROM Diseases d"),
    @NamedQuery(name = "Diseases.findByDiseaseid", query = "SELECT d FROM Diseases d WHERE d.diseaseid = :diseaseid"),
    @NamedQuery(name = "Diseases.findByDiseasename", query = "SELECT d FROM Diseases d WHERE d.diseasename = :diseasename"),
    @NamedQuery(name = "Diseases.findByDiseasetype", query = "SELECT d FROM Diseases d WHERE d.diseasetype = :diseasetype")})
public class Diseases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "diseaseid")
    private Integer diseaseid;
    @Size(max = 50)
    @Column(name = "diseasename")
    private String diseasename;
    @Column(name = "diseasetype")
    private Boolean diseasetype;

    public Diseases() {
    }

    public Diseases(Integer diseaseid) {
        this.diseaseid = diseaseid;
    }

    public Integer getDiseaseid() {
        return diseaseid;
    }

    public void setDiseaseid(Integer diseaseid) {
        this.diseaseid = diseaseid;
    }

    public String getDiseasename() {
        return diseasename;
    }

    public void setDiseasename(String diseasename) {
        this.diseasename = diseasename;
    }

    public Boolean getDiseasetype() {
        return diseasetype;
    }

    public void setDiseasetype(Boolean diseasetype) {
        this.diseasetype = diseasetype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diseaseid != null ? diseaseid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diseases)) {
            return false;
        }
        Diseases other = (Diseases) object;
        if ((this.diseaseid == null && other.diseaseid != null) || (this.diseaseid != null && !this.diseaseid.equals(other.diseaseid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.Diseases[ diseaseid=" + diseaseid + " ]";
    }
    
}
