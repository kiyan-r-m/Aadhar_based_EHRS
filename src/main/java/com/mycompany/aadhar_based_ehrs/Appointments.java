/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aadhar_based_ehrs;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "appointments", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Appointments.findAll", query = "SELECT a FROM Appointments a"),
    @NamedQuery(name = "Appointments.findByAppointmentid", query = "SELECT a FROM Appointments a WHERE a.appointmentid = :appointmentid"),
    @NamedQuery(name = "Appointments.findByDoctorid", query = "SELECT a FROM Appointments a WHERE a.doctorid = :doctorid"),
    @NamedQuery(name = "Appointments.findByPatientid", query = "SELECT a FROM Appointments a WHERE a.patientid = :patientid"),
    @NamedQuery(name = "Appointments.findByAppointmentdate", query = "SELECT a FROM Appointments a WHERE a.appointmentdate = :appointmentdate"),
    @NamedQuery(name = "Appointments.findByDuration", query = "SELECT a FROM Appointments a WHERE a.duration = :duration"),
    @NamedQuery(name = "Appointments.findByIsattended", query = "SELECT a FROM Appointments a WHERE a.isattended = :isattended")})
public class Appointments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "appointmentid")
    private Integer appointmentid;
    @Column(name = "doctorid")
    private Integer doctorid;
    @Column(name = "patientid")
    private Integer patientid;
    @Column(name = "appointmentdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentdate;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "isattended")
    private Boolean isattended;

    public Appointments() {
    }

    public Appointments(Integer appointmentid) {
        this.appointmentid = appointmentid;
    }

    public Integer getAppointmentid() {
        return appointmentid;
    }

    public void setAppointmentid(Integer appointmentid) {
        this.appointmentid = appointmentid;
    }

    public Integer getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }

    public Integer getPatientid() {
        return patientid;
    }

    public void setPatientid(Integer patientid) {
        this.patientid = patientid;
    }

    public Date getAppointmentdate() {
        return appointmentdate;
    }

    public void setAppointmentdate(Date appointmentdate) {
        this.appointmentdate = appointmentdate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getIsattended() {
        return isattended;
    }

    public void setIsattended(Boolean isattended) {
        this.isattended = isattended;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentid != null ? appointmentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointments)) {
            return false;
        }
        Appointments other = (Appointments) object;
        if ((this.appointmentid == null && other.appointmentid != null) || (this.appointmentid != null && !this.appointmentid.equals(other.appointmentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aadhar_based_ehrs.Appointments[ appointmentid=" + appointmentid + " ]";
    }
    
}
