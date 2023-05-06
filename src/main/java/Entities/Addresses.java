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
import javax.persistence.Lob;
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
@Table(name = "addresses", catalog = "ehrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Addresses.findAll", query = "SELECT a FROM Addresses a"),
    @NamedQuery(name = "Addresses.findByAddressId", query = "SELECT a FROM Addresses a WHERE a.addressId = :addressId"),
    @NamedQuery(name = "Addresses.findByAddress", query = "SELECT a FROM Addresses a WHERE a.address = :address")})
public class Addresses implements Serializable {

    @Lob
    @Size(max = 65535)
    @Column(name = "address")
    private String address;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "address_id")
    private Integer addressId;
    @JoinColumn(name = "pincode", referencedColumnName = "pincode")
    @ManyToOne
    private Pincodes pincode;
    @OneToMany(mappedBy = "addressId")
    @JsonbTransient
    private Collection<Users> usersCollection;

    public Addresses() {
    }

    public Addresses(Integer addressId) {
        this.addressId = addressId;
    }

    public Addresses(String address, Pincodes pincode) {
        this.address = address;
        this.addressId = 0;
        this.pincode = pincode;
    }

    public Addresses(Integer addressId, String address, Pincodes pincode) {
        this.address = address;
        this.addressId = addressId;
        this.pincode = pincode;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }


    public Pincodes getPincode() {
        return pincode;
    }

    public void setPincode(Pincodes pincode) {
        this.pincode = pincode;
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
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Addresses)) {
            return false;
        }
        Addresses other = (Addresses) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Addresses[ addressId=" + addressId + " ]";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
