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
@Table(name = "allergies", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Allergies.findAll", query = "SELECT a FROM Allergies a"),
    @NamedQuery(name = "Allergies.findByAllergyid", query = "SELECT a FROM Allergies a WHERE a.allergyid = :allergyid"),
    @NamedQuery(name = "Allergies.findByAllergyname", query = "SELECT a FROM Allergies a WHERE a.allergyname = :allergyname")})
public class Allergies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "allergyid")
    private Integer allergyid;
    @Size(max = 50)
    @Column(name = "allergyname")
    private String allergyname;

    public Allergies() {
    }

    public Allergies(Integer allergyid) {
        this.allergyid = allergyid;
    }

    public Integer getAllergyid() {
        return allergyid;
    }

    public void setAllergyid(Integer allergyid) {
        this.allergyid = allergyid;
    }

    public String getAllergyname() {
        return allergyname;
    }

    public void setAllergyname(String allergyname) {
        this.allergyname = allergyname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (allergyid != null ? allergyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Allergies)) {
            return false;
        }
        Allergies other = (Allergies) object;
        if ((this.allergyid == null && other.allergyid != null) || (this.allergyid != null && !this.allergyid.equals(other.allergyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.Allergies[ allergyid=" + allergyid + " ]";
    }
    
}
