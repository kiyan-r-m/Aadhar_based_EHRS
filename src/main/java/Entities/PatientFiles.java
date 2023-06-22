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
 * @author krdmo
 */
@Entity
@Table(name = "patient_files", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "PatientFiles.findAll", query = "SELECT p FROM PatientFiles p"),
    @NamedQuery(name = "PatientFiles.findByFileId", query = "SELECT p FROM PatientFiles p WHERE p.fileId = :fileId"),
    @NamedQuery(name = "PatientFiles.findByReportDate", query = "SELECT p FROM PatientFiles p WHERE p.reportDate = :reportDate")})
public class PatientFiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "file_id")
    private Integer fileId;
    @Lob
    @Size(max = 65535)
    @Column(name = "report_name")
    private String reportName;
    @Lob
    @Size(max = 65535)
    @Column(name = "location")
    private String location;
    @Column(name = "report_date")
    @Temporal(TemporalType.DATE)
    private Date reportDate;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne
    private MedicalReportCategories categoryId;
    @JoinColumn(name = "patient_doctor_mapper_id", referencedColumnName = "patient_doctor_mapper_id")
    @ManyToOne
    private PatientDoctorMapper patientDoctorMapperId;

    public PatientFiles() {
        this.categoryId = new MedicalReportCategories();
        this.reportDate = new Date();
        this.patientDoctorMapperId = new PatientDoctorMapper();
    }

    public PatientFiles(Integer fileId) {
        this.fileId = fileId;
        this.categoryId = new MedicalReportCategories();
        this.reportDate = new Date();
        this.patientDoctorMapperId = new PatientDoctorMapper();
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public PatientDoctorMapper getPatientDoctorMapperId() {
        return patientDoctorMapperId;
    }

    public void setPatientDoctorMapperId(PatientDoctorMapper patientDoctorMapperId) {
        this.patientDoctorMapperId = patientDoctorMapperId;
    }

    public MedicalReportCategories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(MedicalReportCategories categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fileId != null ? fileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientFiles)) {
            return false;
        }
        PatientFiles other = (PatientFiles) object;
        if ((this.fileId == null && other.fileId != null) || (this.fileId != null && !this.fileId.equals(other.fileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PatientFiles[ fileId=" + fileId + " ]";
    }

}
