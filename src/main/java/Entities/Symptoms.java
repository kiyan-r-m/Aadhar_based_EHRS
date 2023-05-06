/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "symptoms", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Symptoms.findAll", query = "SELECT s FROM Symptoms s"),
    @NamedQuery(name = "Symptoms.findBySymptomId", query = "SELECT s FROM Symptoms s WHERE s.symptomId = :symptomId"),
    @NamedQuery(name = "Symptoms.findBySymptomName", query = "SELECT s FROM Symptoms s WHERE s.symptomName = :symptomName")})
public class Symptoms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "symptom_id")
    private Integer symptomId;
    @Lob
    @Size(max = 65535)
    @Column(name = "symptom_name")
    private String symptomName;
    @OneToMany(mappedBy = "symptomId")
    @JsonbTransient
    private Collection<Diseases> diseasesCollection;

    public Symptoms() {
    }

    public Symptoms(Integer symptomId) {
        this.symptomId = symptomId;
    }

    public Symptoms(String symptomName) {
        this.symptomId = 0;
        this.symptomName = symptomName;
    }

    public Symptoms(Integer symptomId, String symptomName) {
        this.symptomId = symptomId;
        this.symptomName = symptomName;
    }

    public Integer getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(Integer symptomId) {
        this.symptomId = symptomId;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
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
        hash += (symptomId != null ? symptomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Symptoms)) {
            return false;
        }
        Symptoms other = (Symptoms) object;
        if ((this.symptomId == null && other.symptomId != null) || (this.symptomId != null && !this.symptomId.equals(other.symptomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Symptoms[ symptomId=" + symptomId + " ]";
    }
    
}
