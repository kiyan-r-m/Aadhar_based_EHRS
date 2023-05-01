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
@Table(name = "education_level", catalog = "ehr_system", schema = "")
@NamedQueries({
    @NamedQuery(name = "EducationLevel.findAll", query = "SELECT e FROM EducationLevel e"),
    @NamedQuery(name = "EducationLevel.findByLevelId", query = "SELECT e FROM EducationLevel e WHERE e.levelId = :levelId")})
public class EducationLevel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "level_id")
    private Integer levelId;
    @Lob
    @Size(max = 65535)
    @Column(name = "level_name")
    private String levelName;
    @OneToMany(mappedBy = "educationLevelId")
    private Collection<DoctorDetails> doctorDetailsCollection;

    public EducationLevel() {
    }

    public EducationLevel(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
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
        hash += (levelId != null ? levelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EducationLevel)) {
            return false;
        }
        EducationLevel other = (EducationLevel) object;
        if ((this.levelId == null && other.levelId != null) || (this.levelId != null && !this.levelId.equals(other.levelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.EducationLevel[ levelId=" + levelId + " ]";
    }
    
}
