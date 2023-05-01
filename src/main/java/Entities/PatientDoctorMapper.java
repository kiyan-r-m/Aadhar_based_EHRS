/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "patient_doctor_mapper", catalog = "ehr_system", schema = "")
@NamedQueries({
    @NamedQuery(name = "PatientDoctorMapper.findAll", query = "SELECT p FROM PatientDoctorMapper p"),
    @NamedQuery(name = "PatientDoctorMapper.findByPatientId", query = "SELECT p FROM PatientDoctorMapper p WHERE p.patientDoctorMapperPK.patientId = :patientId"),
    @NamedQuery(name = "PatientDoctorMapper.findByDoctorId", query = "SELECT p FROM PatientDoctorMapper p WHERE p.patientDoctorMapperPK.doctorId = :doctorId"),
    @NamedQuery(name = "PatientDoctorMapper.findByDiseaseId", query = "SELECT p FROM PatientDoctorMapper p WHERE p.patientDoctorMapperPK.diseaseId = :diseaseId"),
    @NamedQuery(name = "PatientDoctorMapper.findByStartDate", query = "SELECT p FROM PatientDoctorMapper p WHERE p.patientDoctorMapperPK.startDate = :startDate"),
    @NamedQuery(name = "PatientDoctorMapper.findByEndDate", query = "SELECT p FROM PatientDoctorMapper p WHERE p.endDate = :endDate")})
public class PatientDoctorMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatientDoctorMapperPK patientDoctorMapperPK;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "disease_id", referencedColumnName = "disease_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Diseases diseases;
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DoctorDetails doctorDetails;
    @JoinColumn(name = "patient_id", referencedColumnName = "userId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public PatientDoctorMapper() {
    }

    public PatientDoctorMapper(PatientDoctorMapperPK patientDoctorMapperPK) {
        this.patientDoctorMapperPK = patientDoctorMapperPK;
    }

    public PatientDoctorMapper(int patientId, int doctorId, int diseaseId, Date startDate) {
        this.patientDoctorMapperPK = new PatientDoctorMapperPK(patientId, doctorId, diseaseId, startDate);
    }

    public PatientDoctorMapperPK getPatientDoctorMapperPK() {
        return patientDoctorMapperPK;
    }

    public void setPatientDoctorMapperPK(PatientDoctorMapperPK patientDoctorMapperPK) {
        this.patientDoctorMapperPK = patientDoctorMapperPK;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Diseases getDiseases() {
        return diseases;
    }

    public void setDiseases(Diseases diseases) {
        this.diseases = diseases;
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
        hash += (patientDoctorMapperPK != null ? patientDoctorMapperPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientDoctorMapper)) {
            return false;
        }
        PatientDoctorMapper other = (PatientDoctorMapper) object;
        if ((this.patientDoctorMapperPK == null && other.patientDoctorMapperPK != null) || (this.patientDoctorMapperPK != null && !this.patientDoctorMapperPK.equals(other.patientDoctorMapperPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PatientDoctorMapper[ patientDoctorMapperPK=" + patientDoctorMapperPK + " ]";
    }
    
}
