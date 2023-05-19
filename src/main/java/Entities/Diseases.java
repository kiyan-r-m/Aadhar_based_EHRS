/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Diseases.findByCommonMedicationId", query = "SELECT d FROM Diseases d WHERE d.commonMedicationId = :commonMedicationId"),
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
    @Column(name = "common_medication_id")
    private Integer commonMedicationId;
    @Column(name = "disease_type")
    private Boolean diseaseType;
    @JoinTable(name = "disease_symptom_mapper", joinColumns = {
        @JoinColumn(name = "disease_id", referencedColumnName = "disease_id")}, inverseJoinColumns = {
        @JoinColumn(name = "disease_id", referencedColumnName = "symptom_id")})
    @ManyToMany
    private Collection<Symptoms> symptomsCollection;
    @JoinTable(name = "disease_medication_mapper", joinColumns = {
        @JoinColumn(name = "disease_id", referencedColumnName = "disease_id")}, inverseJoinColumns = {
        @JoinColumn(name = "disease_id", referencedColumnName = "medication_id")})
    @ManyToMany
    private Collection<CommonMedications> commonMedicationsCollection;

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

    public Integer getCommonMedicationId() {
        return commonMedicationId;
    }

    public void setCommonMedicationId(Integer commonMedicationId) {
        this.commonMedicationId = commonMedicationId;
    }

    public Boolean getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(Boolean diseaseType) {
        this.diseaseType = diseaseType;
    }

    public Collection<Symptoms> getSymptomsCollection() {
        return symptomsCollection;
    }

    public void setSymptomsCollection(Collection<Symptoms> symptomsCollection) {
        this.symptomsCollection = symptomsCollection;
    }

    public Collection<CommonMedications> getCommonMedicationsCollection() {
        return commonMedicationsCollection;
    }

    public void setCommonMedicationsCollection(Collection<CommonMedications> commonMedicationsCollection) {
        this.commonMedicationsCollection = commonMedicationsCollection;
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
