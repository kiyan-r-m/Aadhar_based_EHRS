/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "patient_access_mapper", catalog = "ehr_system", schema = "")
@NamedQueries({
    @NamedQuery(name = "PatientAccessMapper.findAll", query = "SELECT p FROM PatientAccessMapper p"),
    @NamedQuery(name = "PatientAccessMapper.findByPatientId", query = "SELECT p FROM PatientAccessMapper p WHERE p.patientAccessMapperPK.patientId = :patientId"),
    @NamedQuery(name = "PatientAccessMapper.findByDoctorId", query = "SELECT p FROM PatientAccessMapper p WHERE p.patientAccessMapperPK.doctorId = :doctorId"),
    @NamedQuery(name = "PatientAccessMapper.findByIsActive", query = "SELECT p FROM PatientAccessMapper p WHERE p.isActive = :isActive")})
public class PatientAccessMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatientAccessMapperPK patientAccessMapperPK;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DoctorDetails doctorDetails;
    @JoinColumn(name = "patient_id", referencedColumnName = "userId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public PatientAccessMapper() {
    }

    public PatientAccessMapper(PatientAccessMapperPK patientAccessMapperPK) {
        this.patientAccessMapperPK = patientAccessMapperPK;
    }

    public PatientAccessMapper(int patientId, int doctorId) {
        this.patientAccessMapperPK = new PatientAccessMapperPK(patientId, doctorId);
    }

    public PatientAccessMapperPK getPatientAccessMapperPK() {
        return patientAccessMapperPK;
    }

    public void setPatientAccessMapperPK(PatientAccessMapperPK patientAccessMapperPK) {
        this.patientAccessMapperPK = patientAccessMapperPK;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public DoctorDetails getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(DoctorDetails doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientAccessMapperPK != null ? patientAccessMapperPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientAccessMapper)) {
            return false;
        }
        PatientAccessMapper other = (PatientAccessMapper) object;
        if ((this.patientAccessMapperPK == null && other.patientAccessMapperPK != null) || (this.patientAccessMapperPK != null && !this.patientAccessMapperPK.equals(other.patientAccessMapperPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PatientAccessMapper[ patientAccessMapperPK=" + patientAccessMapperPK + " ]";
    }
    
}
