/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import javax.validation.constraints.Size;

/**
 *
 * @author krdmo
 */
@Entity
@Table(name = "users", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByAadharCardNo", query = "SELECT u FROM Users u WHERE u.aadharCardNo = :aadharCardNo"),
    @NamedQuery(name = "Users.findByContactNo", query = "SELECT u FROM Users u WHERE u.contactNo = :contactNo"),
    @NamedQuery(name = "Users.findByDob", query = "SELECT u FROM Users u WHERE u.dob = :dob"),
    @NamedQuery(name = "Users.findByBloodGroupId", query = "SELECT u FROM Users u WHERE u.bloodGroupId.bloodGroupId = :bloodGroupId"),
    @NamedQuery(name = "Users.findByAddressId", query = "SELECT u FROM Users u WHERE u.addressId.addressId = :addressId"),
    @NamedQuery(name = "Users.findByAadharPassword", query = "SELECT u FROM Users u where u.aadharCardNo = :aadharCardNo AND u.password = :password"),
    @NamedQuery(name = "Users.findByUsernamePassword", query = "SELECT u FROM Users u where u.username = :username AND u.password = :password"),
    @NamedQuery(name = "Users.findByEmailId", query = "SELECT u FROM Users u where u.emailid = :emailId")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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
    @JsonbTransient
    private Collection<Diseases> diseasesCollection;
    @ManyToMany(mappedBy = "usersCollection")
    @JsonbTransient
    private Collection<DoctorDetails> doctorDetailsCollection;
    @ManyToMany(mappedBy = "usersCollection")
    @JsonbTransient
    private Collection<Allergies> allergiesCollection;
    @OneToMany(mappedBy = "patientId")
    @JsonbTransient
    private Collection<Appointments> appointmentsCollection;
    @OneToMany(mappedBy = "userId")
    @JsonbTransient
    private Collection<DoctorDetails> doctorDetailsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    @JsonbTransient
    private Collection<PatientDoctorMapper> patientDoctorMapperCollection;
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @ManyToOne
    private Addresses addressId;
    @JoinColumn(name = "blood_group_id", referencedColumnName = "blood_group_id")
    @ManyToOne
    private BloodGroups bloodGroupId;
    @JoinColumn(name = "role_id", referencedColumnName = "roleid")
    @ManyToOne
    private Roles roleId;

    public Users() {
        this.gender = "Male";
        this.diseasesCollection = new ArrayList<>();
        this.allergiesCollection = new ArrayList<>();
    }

    public Users(Integer userId) {
        this.userId = userId;
        this.gender = "Male";
        this.diseasesCollection = new ArrayList<>();
        this.allergiesCollection = new ArrayList<>();
    }

    public Users(String username, String emailid, String password, BigInteger aadharCardNo, BigInteger contactNo, String gender, Date dob, Collection<Diseases> diseasesCollection, Collection<Allergies> allergiesCollection, Addresses addressId, BloodGroups bloodGroupId, Roles roleId) {
        this.userId = 0;
        this.username = username;
        this.emailid = emailid;
        this.password = password;
        this.aadharCardNo = aadharCardNo;
        this.contactNo = contactNo;
        this.gender = gender;
        this.dob = dob;
        this.diseasesCollection = diseasesCollection;
        this.allergiesCollection = allergiesCollection;
        this.addressId = addressId;
        this.bloodGroupId = bloodGroupId;
        this.roleId = roleId;
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

    public Collection<DoctorDetails> getDoctorDetailsCollection() {
        return doctorDetailsCollection;
    }

    public void setDoctorDetailsCollection(Collection<DoctorDetails> doctorDetailsCollection) {
        this.doctorDetailsCollection = doctorDetailsCollection;
    }

    public Collection<Allergies> getAllergiesCollection() {
        return allergiesCollection;
    }

    public void setAllergiesCollection(Collection<Allergies> allergiesCollection) {
        this.allergiesCollection = allergiesCollection;
    }

    public Collection<Appointments> getAppointmentsCollection() {
        return appointmentsCollection;
    }

    public void setAppointmentsCollection(Collection<Appointments> appointmentsCollection) {
        this.appointmentsCollection = appointmentsCollection;
    }

    public Collection<DoctorDetails> getDoctorDetailsCollection1() {
        return doctorDetailsCollection1;
    }

    public void setDoctorDetailsCollection1(Collection<DoctorDetails> doctorDetailsCollection1) {
        this.doctorDetailsCollection1 = doctorDetailsCollection1;
    }

    public Collection<PatientDoctorMapper> getPatientDoctorMapperCollection() {
        return patientDoctorMapperCollection;
    }

    public void setPatientDoctorMapperCollection(Collection<PatientDoctorMapper> patientDoctorMapperCollection) {
        this.patientDoctorMapperCollection = patientDoctorMapperCollection;
    }

    public Addresses getAddressId() {
        return addressId;
    }

    public void setAddressId(Addresses addressId) {
        this.addressId = addressId;
    }

    public BloodGroups getBloodGroupId() {
        return bloodGroupId;
    }

    public void setBloodGroupId(BloodGroups bloodGroupId) {
        this.bloodGroupId = bloodGroupId;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
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
