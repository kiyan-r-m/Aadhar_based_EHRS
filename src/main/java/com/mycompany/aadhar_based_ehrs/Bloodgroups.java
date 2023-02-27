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
@Table(name = "bloodgroups", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Bloodgroups.findAll", query = "SELECT b FROM Bloodgroups b"),
    @NamedQuery(name = "Bloodgroups.findByBloodgroupid", query = "SELECT b FROM Bloodgroups b WHERE b.bloodgroupid = :bloodgroupid"),
    @NamedQuery(name = "Bloodgroups.findByBloodgroupname", query = "SELECT b FROM Bloodgroups b WHERE b.bloodgroupname = :bloodgroupname")})
public class Bloodgroups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bloodgroupid")
    private Integer bloodgroupid;
    @Size(max = 5)
    @Column(name = "bloodgroupname")
    private String bloodgroupname;

    public Bloodgroups() {
    }

    public Bloodgroups(Integer bloodgroupid) {
        this.bloodgroupid = bloodgroupid;
    }

    public Integer getBloodgroupid() {
        return bloodgroupid;
    }

    public void setBloodgroupid(Integer bloodgroupid) {
        this.bloodgroupid = bloodgroupid;
    }

    public String getBloodgroupname() {
        return bloodgroupname;
    }

    public void setBloodgroupname(String bloodgroupname) {
        this.bloodgroupname = bloodgroupname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bloodgroupid != null ? bloodgroupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bloodgroups)) {
            return false;
        }
        Bloodgroups other = (Bloodgroups) object;
        if ((this.bloodgroupid == null && other.bloodgroupid != null) || (this.bloodgroupid != null && !this.bloodgroupid.equals(other.bloodgroupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.Bloodgroups[ bloodgroupid=" + bloodgroupid + " ]";
    }
    
}
