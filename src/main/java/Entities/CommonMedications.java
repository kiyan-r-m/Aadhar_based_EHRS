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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "common_medications", catalog = "ehr_system", schema = "")
@NamedQueries({
    @NamedQuery(name = "CommonMedications.findAll", query = "SELECT c FROM CommonMedications c"),
    @NamedQuery(name = "CommonMedications.findByMedicationId", query = "SELECT c FROM CommonMedications c WHERE c.medicationId = :medicationId")})
public class CommonMedications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "medication_id")
    private Integer medicationId;
    @Lob
    @Size(max = 65535)
    @Column(name = "medication_name")
    private String medicationName;
    @OneToMany(mappedBy = "commonMedicationId")
    private Collection<Diseases> diseasesCollection;

    public CommonMedications() {
    }

    public CommonMedications(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public Collection<Diseases> getDiseasesCollection() {
        return diseasesCollection;
    }

    public void setDiseasesCollection(Collection<Diseases> diseasesCollection) {
        this.diseasesCollection = diseasesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicationId != null ? medicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommonMedications)) {
            return false;
        }
        CommonMedications other = (CommonMedications) object;
        if ((this.medicationId == null && other.medicationId != null) || (this.medicationId != null && !this.medicationId.equals(other.medicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.CommonMedications[ medicationId=" + medicationId + " ]";
    }
    
}
