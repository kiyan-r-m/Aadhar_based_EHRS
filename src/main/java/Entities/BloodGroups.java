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
@Table(name = "blood_groups", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "BloodGroups.findAll", query = "SELECT b FROM BloodGroups b"),
    @NamedQuery(name = "BloodGroups.findByBloodGroupId", query = "SELECT b FROM BloodGroups b WHERE b.bloodGroupId = :bloodGroupId"),
    @NamedQuery(name = "BloodGroups.findByBloodGroupName", query = "SELECT b FROM BloodGroups b WHERE b.bloodGroupName = :bloodGroupName")})
public class BloodGroups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "blood_group_id")
    private Integer bloodGroupId;
    @Lob
    @Size(max = 65535)
    @Column(name = "blood_group_name")
    private String bloodGroupName;
    @OneToMany(mappedBy = "bloodGroupId")
    @JsonbTransient
    private Collection<Users> usersCollection;

    public BloodGroups() {
    }

    public BloodGroups(Integer bloodGroupId) {
        this.bloodGroupId = bloodGroupId;
    }

    public BloodGroups(String bloodGroupName) {
        this.bloodGroupId = 0;
        this.bloodGroupName = bloodGroupName;
    }
    
    public BloodGroups(Integer bloodGroupId, String bloodGroupName) {
        this.bloodGroupId = bloodGroupId;
        this.bloodGroupName = bloodGroupName;
    }

    public Integer getBloodGroupId() {
        return bloodGroupId;
    }

    public void setBloodGroupId(Integer bloodGroupId) {
        this.bloodGroupId = bloodGroupId;
    }

    public String getBloodGroupName() {
        return bloodGroupName;
    }

    public void setBloodGroupName(String bloodGroupName) {
        this.bloodGroupName = bloodGroupName;
    }

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bloodGroupId != null ? bloodGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BloodGroups)) {
            return false;
        }
        BloodGroups other = (BloodGroups) object;
        if ((this.bloodGroupId == null && other.bloodGroupId != null) || (this.bloodGroupId != null && !this.bloodGroupId.equals(other.bloodGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.BloodGroups[ bloodGroupId=" + bloodGroupId + " ]";
    }
    
}
