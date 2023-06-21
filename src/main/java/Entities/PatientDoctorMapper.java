/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author krdmo
 */
@Entity
@Table(name = "patient_doctor_mapper", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "PatientDoctorMapper.findAll", query = "SELECT p FROM PatientDoctorMapper p"),
    @NamedQuery(name = "PatientDoctorMapper.findByPatientDoctorMapperId", query = "SELECT p FROM PatientDoctorMapper p WHERE p.patientDoctorMapperId = :patientDoctorMapperId"),
    @NamedQuery(name = "PatientDoctorMapper.findByStartDate", query = "SELECT p FROM PatientDoctorMapper p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "PatientDoctorMapper.findByPatientIdDoctorIdDiseaseIdStartDate", query = "SELECT p FROM PatientDoctorMapper p WHERE p.patientId.userId = :patientId AND p.doctorId.doctorId = :doctorId AND p.diseaseId.diseaseId = :diseaseId AND p.startDate = :startDate"),
    @NamedQuery(name = "PatientDoctorMapper.findByEndDate", query = "SELECT p FROM PatientDoctorMapper p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "PatientDoctorMapper.findByPatientId", query = "SELECT p FROM PatientDoctorMapper p WHERE p.patientId = :patientId")})
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "frequencyByDiseaseDateState",
            procedureName = "ehrsystem.frequencyByDiseaseDateState", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "diseaseval", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "dateval", type = LocalDate.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "stateval", type = String.class)
            }
    ),
    @NamedStoredProcedureQuery(name = "topTenDiseasesByCases",
            procedureName = "ehrsystem.topTenDiseasesByCases"
    ),
    @NamedStoredProcedureQuery(name = "countChronicPatients",
            procedureName = "ehrsystem.countChronicPatients"
    ),
    @NamedStoredProcedureQuery(name = "countAcutePatients",
            procedureName = "ehrsystem.countAcutePatients"
    )
})
public class PatientDoctorMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "patient_doctor_mapper_id")
    private Integer patientDoctorMapperId;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "disease_id", referencedColumnName = "disease_id")
    @ManyToOne(optional = false)
    private Diseases diseaseId;
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    @ManyToOne(optional = false)
    private DoctorDetails doctorId;
    @JoinColumn(name = "patient_id", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private Users patientId;
    @OneToMany(mappedBy = "patientDoctorMapperId")
    @JsonbTransient
    private Collection<PatientDiseaseMedication> patientDiseaseMedicationCollection;
    @OneToMany(mappedBy = "patientDoctorMapperId")
    @JsonbTransient
    private Collection<PatientFiles> patientFilesCollection;
    @OneToMany(mappedBy = "patientDoctorMapperId")
    @JsonbTransient
    private Collection<DoctorNotes> doctorNotesCollection;

    public PatientDoctorMapper() {
    }

    public PatientDoctorMapper(Integer patientDoctorMapperId) {
        this.patientDoctorMapperId = patientDoctorMapperId;
    }

    public Integer getPatientDoctorMapperId() {
        return patientDoctorMapperId;
    }

    public void setPatientDoctorMapperId(Integer patientDoctorMapperId) {
        this.patientDoctorMapperId = patientDoctorMapperId;
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

    public Diseases getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Diseases diseaseId) {
        this.diseaseId = diseaseId;
    }

    public DoctorDetails getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(DoctorDetails doctorId) {
        this.doctorId = doctorId;
    }

    public Users getPatientId() {
        return patientId;
    }

    public void setPatientId(Users patientId) {
        this.patientId = patientId;
    }

    public Collection<PatientDiseaseMedication> getPatientDiseaseMedicationCollection() {
        return patientDiseaseMedicationCollection;
    }

    public void setPatientDiseaseMedicationCollection(Collection<PatientDiseaseMedication> patientDiseaseMedicationCollection) {
        this.patientDiseaseMedicationCollection = patientDiseaseMedicationCollection;
    }

    public Collection<PatientFiles> getPatientFilesCollection() {
        return patientFilesCollection;
    }

    public void setPatientFilesCollection(Collection<PatientFiles> patientFilesCollection) {
        this.patientFilesCollection = patientFilesCollection;
    }

    public Collection<DoctorNotes> getDoctorNotesCollection() {
        return doctorNotesCollection;
    }

    public void setDoctorNotesCollection(Collection<DoctorNotes> doctorNotesCollection) {
        this.doctorNotesCollection = doctorNotesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientDoctorMapperId != null ? patientDoctorMapperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientDoctorMapper)) {
            return false;
        }
        PatientDoctorMapper other = (PatientDoctorMapper) object;
        if ((this.patientDoctorMapperId == null && other.patientDoctorMapperId != null) || (this.patientDoctorMapperId != null && !this.patientDoctorMapperId.equals(other.patientDoctorMapperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PatientDoctorMapper[ patientDoctorMapperId=" + patientDoctorMapperId + " ]";
    }

}
