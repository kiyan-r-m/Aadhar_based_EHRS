/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
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
@Table(name = "doctor_details", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "DoctorDetails.findAll", query = "SELECT d FROM DoctorDetails d"),
    @NamedQuery(name = "DoctorDetails.findByDoctorId", query = "SELECT d FROM DoctorDetails d WHERE d.doctorId = :doctorId")})
public class DoctorDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Lob
    @Size(max = 65535)
    @Column(name = "licence_no")
    private String licenceNo;
    @JoinTable(name = "patient_access_mapper", joinColumns = {
        @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")}, inverseJoinColumns = {
        @JoinColumn(name = "patient_id", referencedColumnName = "userId")})
    @ManyToMany
    @JsonbTransient
    private Collection<Users> usersCollection;
    @OneToMany(mappedBy = "doctorId")
    private Collection<Appointments> appointmentsCollection;
    @JoinColumn(name = "degree_id", referencedColumnName = "degree_id")
    @ManyToOne
    private Degrees degreeId;
    @JoinColumn(name = "education_level_id", referencedColumnName = "level_id")
    @ManyToOne
    private EducationLevel educationLevelId;
    @JoinColumn(name = "field_of_study_id", referencedColumnName = "field_id")
    @ManyToOne
    private FieldOfStudy fieldOfStudyId;
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @ManyToOne
    private Users userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorId")
    @JsonbTransient
    private Collection<PatientDoctorMapper> patientDoctorMapperCollection;

    public DoctorDetails() {
    }

    public DoctorDetails(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    public Collection<Appointments> getAppointmentsCollection() {
        return appointmentsCollection;
    }

    public void setAppointmentsCollection(Collection<Appointments> appointmentsCollection) {
        this.appointmentsCollection = appointmentsCollection;
    }

    public Degrees getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Degrees degreeId) {
        this.degreeId = degreeId;
    }

    public EducationLevel getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(EducationLevel educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public FieldOfStudy getFieldOfStudyId() {
        return fieldOfStudyId;
    }

    public void setFieldOfStudyId(FieldOfStudy fieldOfStudyId) {
        this.fieldOfStudyId = fieldOfStudyId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Collection<PatientDoctorMapper> getPatientDoctorMapperCollection() {
        return patientDoctorMapperCollection;
    }

    public void setPatientDoctorMapperCollection(Collection<PatientDoctorMapper> patientDoctorMapperCollection) {
        this.patientDoctorMapperCollection = patientDoctorMapperCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doctorId != null ? doctorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorDetails)) {
            return false;
        }
        DoctorDetails other = (DoctorDetails) object;
        if ((this.doctorId == null && other.doctorId != null) || (this.doctorId != null && !this.doctorId.equals(other.doctorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.DoctorDetails[ doctorId=" + doctorId + " ]";
    }
    
}
