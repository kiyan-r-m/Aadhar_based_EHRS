/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aadhar_based_ehrs;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "pincodes", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Pincodes.findAll", query = "SELECT p FROM Pincodes p"),
    @NamedQuery(name = "Pincodes.findByPin", query = "SELECT p FROM Pincodes p WHERE p.pin = :pin"),
    @NamedQuery(name = "Pincodes.findByDistrictid", query = "SELECT p FROM Pincodes p WHERE p.districtid = :districtid"),
    @NamedQuery(name = "Pincodes.findByStateid", query = "SELECT p FROM Pincodes p WHERE p.stateid = :stateid")})
public class Pincodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pin")
    private Integer pin;
    @Column(name = "districtid")
    private Integer districtid;
    @Column(name = "stateid")
    private Integer stateid;

    public Pincodes() {
    }

    public Pincodes(Integer pin) {
        this.pin = pin;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public Integer getStateid() {
        return stateid;
    }

    public void setStateid(Integer stateid) {
        this.stateid = stateid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pin != null ? pin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pincodes)) {
            return false;
        }
        Pincodes other = (Pincodes) object;
        if ((this.pin == null && other.pin != null) || (this.pin != null && !this.pin.equals(other.pin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.Pincodes[ pin=" + pin + " ]";
    }
    
}
