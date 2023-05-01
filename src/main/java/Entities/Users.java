/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "users", catalog = "ehr_system", schema = "")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByAadharCardNo", query = "SELECT u FROM Users u WHERE u.aadharCardNo = :aadharCardNo"),
    @NamedQuery(name = "Users.findByContactNo", query = "SELECT u FROM Users u WHERE u.contactNo = :contactNo"),
    @NamedQuery(name = "Users.findByDob", query = "SELECT u FROM Users u WHERE u.dob = :dob")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "userId")
    private Integer userId;
    @Lob
    @Size(max = 65535)
    @Column(name = "username")
    private String username;
    @Lob
    @Size(max = 65535)
    @Column(name = "emailid")
    private String emailid;
    @Lob
    @Size(max = 65535)
    @Column(name = "password")
    private String password;
    @Column(name = "aadhar_card_no")
    private BigInteger aadharCardNo;
    @Column(name = "contact_no")
    private BigInteger contactNo;
    @Lob
    @Size(max = 65535)
    @Column(name = "gender")
    private String gender;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Diseases> diseasesCollection;
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<BloodGroups> bloodGroupsCollection;
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Allergies> allergiesCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Addresses> addressesCollection;
    @OneToMany(mappedBy = "patientId")
    private Collection<Appointments> appointmentsCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<DoctorDetails> doctorDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<PatientDoctorMapper> patientDoctorMapperCollection;
    @JoinColumn(name = "role_id", referencedColumnName = "roleid")
    @ManyToOne
    private Roles roleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<PatientAccessMapper> patientAccessMapperCollection;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigInteger getAadharCardNo() {
        return aadharCardNo;
    }

    public void setAadharCardNo(BigInteger aadharCardNo) {
        this.aadharCardNo = aadharCardNo;
    }

    public BigInteger getContactNo() {
        return contactNo;
    }

    public void setContactNo(BigInteger contactNo) {
        this.contactNo = contactNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Collection<Diseases> getDiseasesCollection() {
        return diseasesCollection;
    }

    public void setDiseasesCollection(Collection<Diseases> diseasesCollection) {
        this.diseasesCollection = diseasesCollection;
    }

    public Collection<BloodGroups> getBloodGroupsCollection() {
        return bloodGroupsCollection;
    }

    public void setBloodGroupsCollection(Collection<BloodGroups> bloodGroupsCollection) {
        this.bloodGroupsCollection = bloodGroupsCollection;
    }

    public Collection<Allergies> getAllergiesCollection() {
        return allergiesCollection;
    }

    public void setAllergiesCollection(Collection<Allergies> allergiesCollection) {
        this.allergiesCollection = allergiesCollection;
    }

    public Collection<Addresses> getAddressesCollection() {
        return addressesCollection;
    }

    public void setAddressesCollection(Collection<Addresses> addressesCollection) {
        this.addressesCollection = addressesCollection;
    }

    public Collection<Appointments> getAppointmentsCollection() {
        return appointmentsCollection;
    }

    public void setAppointmentsCollection(Collection<Appointments> appointmentsCollection) {
        this.appointmentsCollection = appointmentsCollection;
    }

    public Collection<DoctorDetails> getDoctorDetailsCollection() {
        return doctorDetailsCollection;
    }

    public void setDoctorDetailsCollection(Collection<DoctorDetails> doctorDetailsCollection) {
        this.doctorDetailsCollection = doctorDetailsCollection;
    }

    public Collection<PatientDoctorMapper> getPatientDoctorMapperCollection() {
        return patientDoctorMapperCollection;
    }

    public void setPatientDoctorMapperCollection(Collection<PatientDoctorMapper> patientDoctorMapperCollection) {
        this.patientDoctorMapperCollection = patientDoctorMapperCollection;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    public Collection<PatientAccessMapper> getPatientAccessMapperCollection() {
        return patientAccessMapperCollection;
    }

    public void setPatientAccessMapperCollection(Collection<PatientAccessMapper> patientAccessMapperCollection) {
        this.patientAccessMapperCollection = patientAccessMapperCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Users[ userId=" + userId + " ]";
    }
    
}
