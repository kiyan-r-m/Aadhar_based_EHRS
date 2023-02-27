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
@Table(name = "doctordetails", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Doctordetails.findAll", query = "SELECT d FROM Doctordetails d"),
    @NamedQuery(name = "Doctordetails.findByDoctorid", query = "SELECT d FROM Doctordetails d WHERE d.doctorid = :doctorid"),
    @NamedQuery(name = "Doctordetails.findByLicenseno", query = "SELECT d FROM Doctordetails d WHERE d.licenseno = :licenseno"),
    @NamedQuery(name = "Doctordetails.findByDegreeid", query = "SELECT d FROM Doctordetails d WHERE d.degreeid = :degreeid"),
    @NamedQuery(name = "Doctordetails.findByFieldid", query = "SELECT d FROM Doctordetails d WHERE d.fieldid = :fieldid"),
    @NamedQuery(name = "Doctordetails.findByUserid", query = "SELECT d FROM Doctordetails d WHERE d.userid = :userid")})
public class Doctordetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doctorid")
    private Integer doctorid;
    @Size(max = 32)
    @Column(name = "licenseno")
    private String licenseno;
    @Column(name = "degreeid")
    private Integer degreeid;
    @Column(name = "fieldid")
    private Integer fieldid;
    @Column(name = "userid")
    private Integer userid;

    public Doctordetails() {
    }

    public Doctordetails(Integer doctorid) {
        this.doctorid = doctorid;
    }

    public Integer getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }

    public String getLicenseno() {
        return licenseno;
    }

    public void setLicenseno(String licenseno) {
        this.licenseno = licenseno;
    }

    public Integer getDegreeid() {
        return degreeid;
    }

    public void setDegreeid(Integer degreeid) {
        this.degreeid = degreeid;
    }

    public Integer getFieldid() {
        return fieldid;
    }

    public void setFieldid(Integer fieldid) {
        this.fieldid = fieldid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doctorid != null ? doctorid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctordetails)) {
            return false;
        }
        Doctordetails other = (Doctordetails) object;
        if ((this.doctorid == null && other.doctorid != null) || (this.doctorid != null && !this.doctorid.equals(other.doctorid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.Doctordetails[ doctorid=" + doctorid + " ]";
    }
    
}
