/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "patient_disease_medication", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "PatientDiseaseMedication.findAll", query = "SELECT p FROM PatientDiseaseMedication p"),
    @NamedQuery(name = "PatientDiseaseMedication.findByPatientDiseaseMedicationId", query = "SELECT p FROM PatientDiseaseMedication p WHERE p.patientDiseaseMedicationId = :patientDiseaseMedicationId"),
    @NamedQuery(name = "PatientDiseaseMedication.findByStartDate", query = "SELECT p FROM PatientDiseaseMedication p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "PatientDiseaseMedication.findByEndDate", query = "SELECT p FROM PatientDiseaseMedication p WHERE p.endDate = :endDate")})
public class PatientDiseaseMedication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "patient_disease_medication_id")
    private Integer patientDiseaseMedicationId;
    @Lob
    @Size(max = 65535)
    @Column(name = "frequency")
    private String frequency;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "medication_id", referencedColumnName = "medication_id")
    @ManyToOne
    private CommonMedications medicationId;
    @JoinColumn(name = "patient_doctor_mapper_id", referencedColumnName = "patient_doctor_mapper_id")
    @ManyToOne
    private PatientDoctorMapper patientDoctorMapperId;

    public PatientDiseaseMedication() {
    }

    public PatientDiseaseMedication(Integer patientDiseaseMedicationId) {
        this.patientDiseaseMedicationId = patientDiseaseMedicationId;
    }

    public Integer getPatientDiseaseMedicationId() {
        return patientDiseaseMedicationId;
    }

    public void setPatientDiseaseMedicationId(Integer patientDiseaseMedicationId) {
        this.patientDiseaseMedicationId = patientDiseaseMedicationId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CommonMedications getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(CommonMedications medicationId) {
        this.medicationId = medicationId;
    }

    public PatientDoctorMapper getPatientDoctorMapperId() {
        return patientDoctorMapperId;
    }

    public void setPatientDoctorMapperId(PatientDoctorMapper patientDoctorMapperId) {
        this.patientDoctorMapperId = patientDoctorMapperId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientDiseaseMedicationId != null ? patientDiseaseMedicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientDiseaseMedication)) {
            return false;
        }
        PatientDiseaseMedication other = (PatientDiseaseMedication) object;
        if ((this.patientDiseaseMedicationId == null && other.patientDiseaseMedicationId != null) || (this.patientDiseaseMedicationId != null && !this.patientDiseaseMedicationId.equals(other.patientDiseaseMedicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PatientDiseaseMedication[ patientDiseaseMedicationId=" + patientDiseaseMedicationId + " ]";
    }
    
}
