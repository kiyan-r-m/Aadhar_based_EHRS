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
@Table(name = "degrees", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Degrees.findAll", query = "SELECT d FROM Degrees d"),
    @NamedQuery(name = "Degrees.findByDegreeId", query = "SELECT d FROM Degrees d WHERE d.degreeId = :degreeId"),
    @NamedQuery(name = "Degrees.findByDegreeName", query = "SELECT d FROM Degrees d WHERE d.degreeName = :degreeName")})
public class Degrees implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "degree_id")
    private Integer degreeId;
    @Lob
    @Size(max = 65535)
    @Column(name = "degree_name")
    private String degreeName;
    @OneToMany(mappedBy = "degreeId")
    @JsonbTransient
    private Collection<DoctorDetails> doctorDetailsCollection;

    public Degrees() {
    }

    public Degrees(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public Degrees(String degreeName) {
        this.degreeId = 0;
        this.degreeName = degreeName;
    }

    public Degrees(Integer degreeId, String degreeName) {
        this.degreeId = degreeId;
        this.degreeName = degreeName;
    }
    
    public Integer getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
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
        hash += (degreeId != null ? degreeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Degrees)) {
            return false;
        }
        Degrees other = (Degrees) object;
        if ((this.degreeId == null && other.degreeId != null) || (this.degreeId != null && !this.degreeId.equals(other.degreeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Degrees[ degreeId=" + degreeId + " ]";
    }
    
}
