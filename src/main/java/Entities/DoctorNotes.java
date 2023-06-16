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
 * @author admin
 */
@Entity
@Table(name = "doctor_notes", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "DoctorNotes.findAll", query = "SELECT d FROM DoctorNotes d"),
    @NamedQuery(name = "DoctorNotes.findByNoteId", query = "SELECT d FROM DoctorNotes d WHERE d.noteId = :noteId"),
    @NamedQuery(name = "DoctorNotes.findByCreatedDate", query = "SELECT d FROM DoctorNotes d WHERE d.createdDate = :createdDate")})
public class DoctorNotes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "note_id")
    private Integer noteId;
    @Lob
    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @JoinColumn(name = "patient_doctor_mapper_id", referencedColumnName = "patient_doctor_mapper_id")
    @ManyToOne
    private PatientDoctorMapper patientDoctorMapperId;

    public DoctorNotes() {
    }

    public DoctorNotes(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public PatientDoctorMapper getPatientDoctorMapperId() {
        return patientDoctorMapperId;
    }

    public void setPatientDoctorMapperId(PatientDoctorMapper patientDoctorMapperId) {
        this.patientDoctorMapperId = patientDoctorMapperId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noteId != null ? noteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorNotes)) {
            return false;
        }
        DoctorNotes other = (DoctorNotes) object;
        if ((this.noteId == null && other.noteId != null) || (this.noteId != null && !this.noteId.equals(other.noteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.DoctorNotes[ noteId=" + noteId + " ]";
    }
    
}
