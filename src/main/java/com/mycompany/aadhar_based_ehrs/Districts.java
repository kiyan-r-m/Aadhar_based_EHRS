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
@Table(name = "districts", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Districts.findAll", query = "SELECT d FROM Districts d"),
    @NamedQuery(name = "Districts.findByDistrictid", query = "SELECT d FROM Districts d WHERE d.districtid = :districtid"),
    @NamedQuery(name = "Districts.findByDistrictname", query = "SELECT d FROM Districts d WHERE d.districtname = :districtname")})
public class Districts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "districtid")
    private Integer districtid;
    @Size(max = 32)
    @Column(name = "districtname")
    private String districtname;

    public Districts() {
    }

    public Districts(Integer districtid) {
        this.districtid = districtid;
    }

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (districtid != null ? districtid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Districts)) {
            return false;
        }
        Districts other = (Districts) object;
        if ((this.districtid == null && other.districtid != null) || (this.districtid != null && !this.districtid.equals(other.districtid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.Districts[ districtid=" + districtid + " ]";
    }
    
}
