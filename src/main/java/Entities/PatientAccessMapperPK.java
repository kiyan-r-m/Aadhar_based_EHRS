/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author admin
 */
@Embeddable
public class PatientAccessMapperPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "patient_id")
    private int patientId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doctor_id")
    private int doctorId;

    public PatientAccessMapperPK() {
    }

    public PatientAccessMapperPK(int patientId, int doctorId) {
        this.patientId = patientId;
        this.doctorId = doctorId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) patientId;
        hash += (int) doctorId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientAccessMapperPK)) {
            return false;
        }
        PatientAccessMapperPK other = (PatientAccessMapperPK) object;
        if (this.patientId != other.patientId) {
            return false;
        }
        if (this.doctorId != other.doctorId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PatientAccessMapperPK[ patientId=" + patientId + ", doctorId=" + doctorId + " ]";
    }
    
}
