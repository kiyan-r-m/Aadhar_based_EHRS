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
@Table(name = "field_of_study", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "FieldOfStudy.findAll", query = "SELECT f FROM FieldOfStudy f"),
    @NamedQuery(name = "FieldOfStudy.findByFieldId", query = "SELECT f FROM FieldOfStudy f WHERE f.fieldId = :fieldId")})
public class FieldOfStudy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "field_id")
    private Integer fieldId;
    @Lob
    @Size(max = 65535)
    @Column(name = "field_name")
    private String fieldName;
    @OneToMany(mappedBy = "fieldOfStudyId")
    private Collection<DoctorDetails> doctorDetailsCollection;

    public FieldOfStudy() {
    }

    public FieldOfStudy(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Collection<DoctorDetails> getDoctorDetailsCollection() {
        return doctorDetailsCollection;
    }

    public void setDoctorDetailsCollection(Collection<DoctorDetails> doctorDetailsCollection) {
        this.doctorDetailsCollection = doctorDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fieldId != null ? fieldId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FieldOfStudy)) {
            return false;
        }
        FieldOfStudy other = (FieldOfStudy) object;
        if ((this.fieldId == null && other.fieldId != null) || (this.fieldId != null && !this.fieldId.equals(other.fieldId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.FieldOfStudy[ fieldId=" + fieldId + " ]";
    }
    
}
