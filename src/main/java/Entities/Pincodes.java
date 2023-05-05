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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "pincodes", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Pincodes.findAll", query = "SELECT p FROM Pincodes p"),
    @NamedQuery(name = "Pincodes.findByPincode", query = "SELECT p FROM Pincodes p WHERE p.pincode = :pincode")})
public class Pincodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pincode")
    private Integer pincode;
    @OneToMany(mappedBy = "pincode")
    @JsonbTransient
    private Collection<Addresses> addressesCollection;
    @JoinColumn(name = "district_id", referencedColumnName = "district_id")
    @ManyToOne
    private Districts districtId;
    @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    @ManyToOne
    private States stateId;

    public Pincodes() {
    }

    public Pincodes(Integer pincode) {
        this.pincode = pincode;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Collection<Addresses> getAddressesCollection() {
        return addressesCollection;
    }

    public void setAddressesCollection(Collection<Addresses> addressesCollection) {
        this.addressesCollection = addressesCollection;
    }

    public Districts getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Districts districtId) {
        this.districtId = districtId;
    }

    public States getStateId() {
        return stateId;
    }

    public void setStateId(States stateId) {
        this.stateId = stateId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pincode != null ? pincode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pincodes)) {
            return false;
        }
        Pincodes other = (Pincodes) object;
        if ((this.pincode == null && other.pincode != null) || (this.pincode != null && !this.pincode.equals(other.pincode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Pincodes[ pincode=" + pincode + " ]";
    }
    
}
