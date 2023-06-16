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
 * @author admin
 */
@Entity
@Table(name = "medical_report_categories", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "MedicalReportCategories.findAll", query = "SELECT m FROM MedicalReportCategories m"),
    @NamedQuery(name = "MedicalReportCategories.findByCategoryId", query = "SELECT m FROM MedicalReportCategories m WHERE m.categoryId = :categoryId")})
public class MedicalReportCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "category_id")
    private Integer categoryId;
    @Lob
    @Size(max = 65535)
    @Column(name = "report_category")
    private String reportCategory;
    @OneToMany(mappedBy = "categoryId")
    private Collection<PatientFiles> patientFilesCollection;

    public MedicalReportCategories() {
    }

    public MedicalReportCategories(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getReportCategory() {
        return reportCategory;
    }

    public void setReportCategory(String reportCategory) {
        this.reportCategory = reportCategory;
    }

    public Collection<PatientFiles> getPatientFilesCollection() {
        return patientFilesCollection;
    }

    public void setPatientFilesCollection(Collection<PatientFiles> patientFilesCollection) {
        this.patientFilesCollection = patientFilesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicalReportCategories)) {
            return false;
        }
        MedicalReportCategories other = (MedicalReportCategories) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MedicalReportCategories[ categoryId=" + categoryId + " ]";
    }
    
}
