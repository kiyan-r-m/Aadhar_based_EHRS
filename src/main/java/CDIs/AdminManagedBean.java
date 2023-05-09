/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIs;

import Beans.AdminBeanLocal;
import Beans.userBeanLocal;
import Entities.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author admin
 */
@Named(value = "adminManagedBean")
@SessionScoped
public class AdminManagedBean implements Serializable {

    int userId;
    BigInteger aadharCardNo, contactNo;
    String username, emailid, password, gender;
    Date dob;
    Collection<Diseases> diseasesCollection = new ArrayList<>();
    Collection<Allergies> allergiesCollection = new ArrayList<>();
    Addresses userAddressId = new Addresses();
    BloodGroups userBloodGroupId = new BloodGroups();
    Roles userRoleId = new Roles();
    
    @EJB AdminBeanLocal abl;
    @EJB userBeanLocal ubl;
    
    Collection<Diseases> diseases = new ArrayList<>();
    Collection<Allergies> allergies = new ArrayList<>();
    Collection<Addresses> addresses = new ArrayList<>();
    Collection<BloodGroups> bloodGroups = new ArrayList<>();
    Collection<Roles> roles = new ArrayList<>();
    Collection<Users> users = new ArrayList<>();
    Users selectedUser;
    
    BloodGroups selectedBloodGroup;
    int bloodGroupId;
    String bloodGroupName;
    
    Degrees selectedDegree;
    Collection<Degrees> degrees = new ArrayList<>();
    
    public AdminManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        ResponseModel<Collection<Diseases>> res = abl.getAllDiseases();
        if(res.status) {
            diseases = res.data;
        }
        ResponseModel<Collection<Allergies>> res1 = ubl.getAllAllergies();
        if(res1.status) {
            allergies = res1.data;
        }
        ResponseModel<Collection<Addresses>> res2 = ubl.getAllAddresses();
        if(res2.status) {
            addresses = res2.data;
        }
        ResponseModel<Collection<BloodGroups>> res3 = ubl.getAllBloodGroups();
        if(res3.status) {
            bloodGroups = res3.data;
        }
        ResponseModel<Collection<Roles>> res4 = ubl.getAllRoles();
        if(res4.status) {
            roles = res4.data;
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Collection<Allergies> getAllergiesCollection() {
        return allergiesCollection;
    }

    public void setAllergiesCollection(Collection<Allergies> allergiesCollection) {
        this.allergiesCollection = allergiesCollection;
    }

    public Addresses getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Addresses userAddressId) {
        this.userAddressId = userAddressId;
    }

    public BloodGroups getUserBloodGroupId() {
        return userBloodGroupId;
    }

    public void setUserBloodGroupId(BloodGroups userBloodGroupId) {
        this.userBloodGroupId = userBloodGroupId;
    }

    public Roles getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Roles userRoleId) {
        this.userRoleId = userRoleId;
    }

    public AdminBeanLocal getAbl() {
        return abl;
    }

    public void setAbl(AdminBeanLocal abl) {
        this.abl = abl;
    }

    public userBeanLocal getUbl() {
        return ubl;
    }

    public void setUbl(userBeanLocal ubl) {
        this.ubl = ubl;
    }

    public Collection<Diseases> getDiseases() {
        return diseases;
    }

    public void setDiseases(Collection<Diseases> diseases) {
        this.diseases = diseases;
    }

    public Collection<Allergies> getAllergies() {
        return allergies;
    }

    public void setAllergies(Collection<Allergies> allergies) {
        this.allergies = allergies;
    }

    public Collection<Addresses> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Addresses> addresses) {
        this.addresses = addresses;
    }

    public Collection<BloodGroups> getBloodGroups() {
        return bloodGroups;
    }

    public void setBloodGroups(Collection<BloodGroups> bloodGroups) {
        this.bloodGroups = bloodGroups;
    }

    public Collection<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Roles> roles) {
        this.roles = roles;
    }

    public Collection<Users> getUsers() {
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public BloodGroups getSelectedBloodGroup() {
        return selectedBloodGroup;
    }

    public void setSelectedBloodGroup(BloodGroups selectedBloodGroup) {
        this.selectedBloodGroup = selectedBloodGroup;
    }

    public int getBloodGroupId() {
        return bloodGroupId;
    }

    public void setBloodGroupId(int bloodGroupId) {
        this.bloodGroupId = bloodGroupId;
    }

    public String getBloodGroupName() {
        return bloodGroupName;
    }

    public void setBloodGroupName(String bloodGroupName) {
        this.bloodGroupName = bloodGroupName;
    }

    public Degrees getSelectedDegree() {
        return selectedDegree;
    }

    public void setSelectedDegree(Degrees selectedDegree) {
        this.selectedDegree = selectedDegree;
    }

    public Collection<Degrees> getDegrees() {
        return degrees;
    }

    public void setDegrees(Collection<Degrees> degrees) {
        this.degrees = degrees;
    }
    
    public List<Users> getAllUsers() {
        ResponseModel<Collection<Users>> res =  ubl.getAllUsers();
        if (res.status) {
            users = res.data;
        }
        return (List<Users>) users;
    }
    
    public void deleteUser(int id) {
        ResponseModel res = ubl.removeUser(id);
        if(res.status == true) {
            successMessage("Delete User", "Record deleted successfully!");
        } else {
            errorMessage("Error", res.message);
        }
    }
    
    public void openNewUser() {
        this.selectedUser = new Users();
    }
    
    public void saveUser() {
        if(selectedUser.getUserId() == null) {
            diseasesCollection = selectedUser.getDiseasesCollection();
            Collection<Diseases> d = new ArrayList<>();
            for (Diseases diseases1 : diseasesCollection) {
                d.add(new Diseases(diseases1.getDiseaseId()));
            }
            selectedUser.setDiseasesCollection(d);
            Collection<Allergies> a = new ArrayList<>();
            allergiesCollection = selectedUser.getAllergiesCollection();
            for (Allergies allergies1 : allergiesCollection) {
                a.add(new Allergies(allergies1.getAllergyId()));
            }
            selectedUser.setAllergiesCollection(a);
            userAddressId = selectedUser.getAddressId();
            selectedUser.setAddressId(new Addresses(userAddressId.getAddressId()));
            ResponseModel res = ubl.addUser(this.selectedUser);
            if(res.status) {
                successMessage("Add User", "Record added successfully");
            } else {
                errorMessage("Error", res.message);
            }
        } else {
            ResponseModel res = ubl.updateUser(this.selectedUser);
            if(res.status) {
                successMessage("Update User", "Record updated successfully");
            } else {
                errorMessage("Error", res.message);
            }
        }
    }
    
    public List<BloodGroups> getAllBloodGroups() {
        ResponseModel<Collection<BloodGroups>> res =  abl.getAllBloodGroups();
        if (res.status) {
            bloodGroups = res.data;
        }
        return (List<BloodGroups>) bloodGroups;
    }
    
    public void deleteBloodGroup(int id) {
        ResponseModel res = abl.deleteBloodGroup(id);
        if(res.status == true) {
            successMessage("Delete Blood Group", "Record deleted successfully!");
        } else {
            errorMessage("Error", res.message);
        }
    }
    
    public void successMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void errorMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void openNewBloodGroup() {
        this.selectedBloodGroup = new BloodGroups();
    }
    
    public void saveBloodGroup() {
        if(selectedBloodGroup.getBloodGroupId() == null) {
            ResponseModel res = abl.addBloodGroup(selectedBloodGroup);
            if(res.status) {
                successMessage("Add Blood Group", "Record added successfully");
            } else {
                errorMessage("Error", res.message);
            }
        } else {
            ResponseModel res = abl.updateBloodGroup(selectedBloodGroup);
            if(res.status) {
                successMessage("Update Blood Group", "Record updated successfully");
            } else {
                errorMessage("Error", res.message);
            }
        }
    }
    public List<Degrees> getAllDegrees() {
        ResponseModel<Collection<Degrees>> res =  abl.getAllDegrees();
        if (res.status) {
            this.degrees = res.data;
        }
        return (List<Degrees>) degrees;
    }
    
    public void deleteDegree(int id) {
        ResponseModel res = abl.deleteDegree(id);
        if(res.status == true) {
            successMessage("Delete Degree", "Record deleted successfully!");
        } else {
            errorMessage("Error", res.message);
        }
    }
    
    public void openNewDegree() {
        this.selectedDegree = new Degrees();
    }
    
    public void saveDegree() {
        if(selectedDegree.getDegreeId() == null) {
            ResponseModel res = abl.addDegree(selectedDegree);
            if(res.status) {
                successMessage("Add Degree", "Record added successfully");
            } else {
                errorMessage("Error", res.message);
            }
        } else {
            ResponseModel res = abl.updateDegree(selectedDegree);
            if(res.status) {
                successMessage("Update Degree", "Record updated successfully");
            } else {
                errorMessage("Error", res.message);
            }
        }
    }
}
