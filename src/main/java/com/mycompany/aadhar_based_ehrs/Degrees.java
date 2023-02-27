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
@Table(name = "degrees", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Degrees.findAll", query = "SELECT d FROM Degrees d"),
    @NamedQuery(name = "Degrees.findByDegreeid", query = "SELECT d FROM Degrees d WHERE d.degreeid = :degreeid"),
    @NamedQuery(name = "Degrees.findByDegreename", query = "SELECT d FROM Degrees d WHERE d.degreename = :degreename")})
public class Degrees implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "degreeid")
    private Integer degreeid;
    @Size(max = 32)
    @Column(name = "degreename")
    private String degreename;

    public Degrees() {
    }

    public Degrees(Integer degreeid) {
        this.degreeid = degreeid;
    }

    public Integer getDegreeid() {
        return degreeid;
    }

    public void setDegreeid(Integer degreeid) {
        this.degreeid = degreeid;
    }

    public String getDegreename() {
        return degreename;
    }

    public void setDegreename(String degreename) {
        this.degreename = degreename;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (degreeid != null ? degreeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Degrees)) {
            return false;
        }
        Degrees other = (Degrees) object;
        if ((this.degreeid == null && other.degreeid != null) || (this.degreeid != null && !this.degreeid.equals(other.degreeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.Degrees[ degreeid=" + degreeid + " ]";
    }
    
}
