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
 * @author admin
 */
@Entity
@Table(name = "allergies", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Allergies.findAll", query = "SELECT a FROM Allergies a"),
    @NamedQuery(name = "Allergies.findByAllergyId", query = "SELECT a FROM Allergies a WHERE a.allergyId = :allergyId")})
public class Allergies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "allergy_id")
    private Integer allergyId;
    @Lob
    @Size(max = 65535)
    @Column(name = "allergy_name")
    private String allergyName;
    @JoinTable(name = "patient_allergy_mapper", joinColumns = {
        @JoinColumn(name = "allergy_id", referencedColumnName = "allergy_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "userId")})
    @ManyToMany
    @JsonbTransient
    private Collection<Users> usersCollection;

    public Allergies() {
    }

    public Allergies(Integer allergyId) {
        this.allergyId = allergyId;
    }

    public Integer getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(Integer allergyId) {
        this.allergyId = allergyId;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
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
        hash += (allergyId != null ? allergyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Allergies)) {
            return false;
        }
        Allergies other = (Allergies) object;
        if ((this.allergyId == null && other.allergyId != null) || (this.allergyId != null && !this.allergyId.equals(other.allergyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Allergies[ allergyId=" + allergyId + " ]";
    }
    
}
