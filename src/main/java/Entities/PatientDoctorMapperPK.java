/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author admin
 */
@Embeddable
public class PatientDoctorMapperPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "patient_id")
    private int patientId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doctor_id")
    private int doctorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disease_id")
    private int diseaseId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    public PatientDoctorMapperPK() {
    }

    public PatientDoctorMapperPK(int patientId, int doctorId, int diseaseId, Date startDate) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diseaseId = diseaseId;
        this.startDate = startDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) patientId;
        hash += (int) doctorId;
        hash += (int) diseaseId;
        hash += (startDate != null ? startDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientDoctorMapperPK)) {
            return false;
        }
        PatientDoctorMapperPK other = (PatientDoctorMapperPK) object;
        if (this.patientId != other.patientId) {
            return false;
        }
        if (this.doctorId != other.doctorId) {
            return false;
        }
        if (this.diseaseId != other.diseaseId) {
            return false;
        }
        if ((this.startDate == null && other.startDate != null) || (this.startDate != null && !this.startDate.equals(other.startDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PatientDoctorMapperPK[ patientId=" + patientId + ", doctorId=" + doctorId + ", diseaseId=" + diseaseId + ", startDate=" + startDate + " ]";
    }
    
}
