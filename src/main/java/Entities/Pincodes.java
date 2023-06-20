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
    @Lob
    @Size(max = 65535)
    @Column(name = "district")
    private String district;
    @Lob
    @Size(max = 65535)
    @Column(name = "state")
    private String state;
    @OneToMany(mappedBy = "pincode")
    @JsonbTransient
    private Collection<Addresses> addressesCollection;

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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Collection<Addresses> getAddressesCollection() {
        return addressesCollection;
    }

    public void setAddressesCollection(Collection<Addresses> addressesCollection) {
        this.addressesCollection = addressesCollection;
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
