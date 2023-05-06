/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author krdmo
 */
@Entity
@Table(name = "diseases", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Diseases.findAll", query = "SELECT d FROM Diseases d"),
    @NamedQuery(name = "Diseases.findByDiseaseId", query = "SELECT d FROM Diseases d WHERE d.diseaseId = :diseaseId"),
    @NamedQuery(name = "Diseases.findByDiseaseType", query = "SELECT d FROM Diseases d WHERE d.diseaseType = :diseaseType")})
public class Diseases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "disease_id")
    private Integer diseaseId;
    @Lob
    @Size(max = 65535)
    @Column(name = "disease_name")
    private String diseaseName;
    @Column(name = "disease_type")
    private Boolean diseaseType;
    @JoinTable(name = "patient_chronic_mapper", joinColumns = {
        @JoinColumn(name = "disease_id", referencedColumnName = "disease_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "userId")})
    @ManyToMany
    private Collection<Users> usersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diseaseId")
    private Collection<PatientDoctorMapper> patientDoctorMapperCollection;
    @JoinColumn(name = "common_medication_id", referencedColumnName = "medication_id")
    @ManyToOne
    private CommonMedications commonMedicationId;
    @JoinColumn(name = "symptom_id", referencedColumnName = "symptom_id")
    @ManyToOne
    private Symptoms symptomId;

    public Diseases() {
    }

    public Diseases(Integer diseaseId) {
        this.diseaseId = diseaseId;
    }

    public Integer getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Integer diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public Boolean getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(Boolean diseaseType) {
        this.diseaseType = diseaseType;
    }

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    public Collection<PatientDoctorMapper> getPatientDoctorMapperCollection() {
        return patientDoctorMapperCollection;
    }

    public void setPatientDoctorMapperCollection(Collection<PatientDoctorMapper> patientDoctorMapperCollection) {
        this.patientDoctorMapperCollection = patientDoctorMapperCollection;
    }

    public CommonMedications getCommonMedicationId() {
        return commonMedicationId;
    }

    public void setCommonMedicationId(CommonMedications commonMedicationId) {
        this.commonMedicationId = commonMedicationId;
    }

    public Symptoms getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(Symptoms symptomId) {
        this.symptomId = symptomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diseaseId != null ? diseaseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diseases)) {
            return false;
        }
        Diseases other = (Diseases) object;
        if ((this.diseaseId == null && other.diseaseId != null) || (this.diseaseId != null && !this.diseaseId.equals(other.diseaseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Diseases[ diseaseId=" + diseaseId + " ]";
    }
    
}
