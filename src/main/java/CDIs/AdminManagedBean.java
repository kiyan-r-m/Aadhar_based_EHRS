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
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

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
    BloodGroups userBloodGroupId = new BloodGroups();
    Roles userRoleId = new Roles();
    String Pincode;

    @EJB
    AdminBeanLocal abl;
    @EJB
    userBeanLocal ubl;

    Collection<Diseases> diseases = new ArrayList<>();
    Collection<Allergies> allergies = new ArrayList<>();
//    Collection<Addresses> addresses = new ArrayList<>();
    Collection<BloodGroups> bloodGroups = new ArrayList<>();
    Collection<Roles> roles = new ArrayList<>();
    Collection<Users> users = new ArrayList<>();
    Users selectedUser;
    Collection<Pincodes> pincodes = new ArrayList<>();
    Collection<String> pc = new ArrayList<>();

    BloodGroups selectedBloodGroup;
    int bloodGroupId;
    String bloodGroupName;
    int roleId;

    Degrees selectedDegree;
    Collection<Degrees> degrees = new ArrayList<>();

    CommonMedications selectedMedication;
    Collection<CommonMedications> medications = new ArrayList<>();

    Symptoms selectedSymptom;
    Collection<Symptoms> symptoms = new ArrayList<>();

//    Districts selectedDistrict;
//    Collection<Districts> districts = new ArrayList<>();
    Diseases selectedDisease;

    FieldOfStudy selectedFieldOfStudy;

    public AdminManagedBean() {
    }

    @PostConstruct
    public void init() {
        ResponseModel<Collection<Diseases>> res = abl.getAllDiseases();
        if (res.status) {
            diseases = res.data;
        }
        ResponseModel<Collection<Allergies>> res1 = ubl.getAllAllergies();
        if (res1.status) {
            allergies = res1.data;
        }
//        ResponseModel<Collection<Addresses>> res2 = ubl.getAllAddresses();
//        if (res2.status) {
//            addresses = res2.data;
//        }
        ResponseModel<Collection<Pincodes>> res2 = abl.getAllPincodes();
        if (res2.status) {
            pincodes = res2.data;
            for (Pincodes pincode : pincodes) {
                pc.add(pincode.getPincode().toString());
            }
        }
        ResponseModel<Collection<BloodGroups>> res3 = ubl.getAllBloodGroups();
        if (res3.status) {
            bloodGroups = res3.data;
        }
        ResponseModel<Collection<Roles>> res4 = ubl.getAllRoles();
        if (res4.status) {
            roles = res4.data;
        }
        ResponseModel<Collection<Symptoms>> res5 = abl.getAllSymptoms();
        if (res5.status) {
            symptoms = res5.data;
        }
        ResponseModel<Collection<CommonMedications>> res6 = abl.getAllCommonMedications();
        if (res6.status) {
            medications = res6.data;
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

    public Collection<Pincodes> getPincodes() {
        return pincodes;
    }

    public void setPincodes(Collection<Pincodes> pincodes) {
        this.pincodes = pincodes;
    }

    public Collection<String> getPc() {
        return pc;
    }

//    public Collection<Addresses> getAddresses() {
//        return addresses;
//    }
//
//    public void setAddresses(Collection<Addresses> addresses) {
//        this.addresses = addresses;
//    }
    public void setPc(Collection<String> pc) {
        this.pc = pc;
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

    public CommonMedications getSelectedMedication() {
        return selectedMedication;
    }

    public void setSelectedMedication(CommonMedications selectedMedication) {
        this.selectedMedication = selectedMedication;
    }

    public Collection<CommonMedications> getMedications() {
        return medications;
    }

    public void setMedications(Collection<CommonMedications> medications) {
        this.medications = medications;
    }

    public Symptoms getSelectedSymptom() {
        return selectedSymptom;
    }

    public void setSelectedSymptom(Symptoms selectedSymptom) {
        this.selectedSymptom = selectedSymptom;
    }

    public Collection<Symptoms> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Collection<Symptoms> symptoms) {
        this.symptoms = symptoms;
    }

//    public Districts getSelectedDistrict() {
//        return selectedDistrict;
//    }
//
//    public void setSelectedDistrict(Districts selectedDistrict) {
//        this.selectedDistrict = selectedDistrict;
//    }
//
//    public Collection<Districts> getDistricts() {
//        return districts;
//    }
//
//    public void setDistricts(Collection<Districts> districts) {
//        this.districts = districts;
//    }
    public Diseases getSelectedDisease() {
        return selectedDisease;
    }

    public void setSelectedDisease(Diseases selectedDisease) {
        this.selectedDisease = selectedDisease;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String Pincode) {
        this.Pincode = Pincode;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public FieldOfStudy getSelectedFieldOfStudy() {
        return selectedFieldOfStudy;
    }

    public void setSelectedFieldOfStudy(FieldOfStudy selectedFieldOfStudy) {
        this.selectedFieldOfStudy = selectedFieldOfStudy;
    }

    public List<Users> getAllUsers() {
        ResponseModel<Collection<Users>> res = ubl.getAllUsers();
        if (res.status) {
            users = res.data;
        }
        return (List<Users>) users;
    }

    public void deleteUser(int id) {
        ResponseModel res = ubl.removeUser(id);
        if (res.status == true) {
            successMessage("Delete User", "Record deleted successfully!");
        } else {
            errorMessage("Error", res.message);
        }
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "userTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public void openNewUser() {
        this.selectedUser = new Users();
        selectedUser.setAddressId(new Addresses());
        this.bloodGroupId = 0;
        this.Pincode = null;
        this.roleId = 0;
    }

    public void onPincodeSelect() {
        if (Pincode != null || Pincode.isEmpty()) {
            Pincodes pin = pincodes.stream().filter(p -> p.getPincode().toString().equals(Pincode)).findFirst().orElse(null);

            this.selectedUser.getAddressId().setPincode(pin);
            UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent component = findComponentById(baseComponent, "district");
            PrimeFaces.current().ajax().update(component.getClientId());
            component = findComponentById(baseComponent, "state");
            PrimeFaces.current().ajax().update(component.getClientId());
        } else {
            this.selectedUser.getAddressId().setPincode(new Pincodes());
            UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent component = findComponentById(baseComponent, "district");
            PrimeFaces.current().ajax().update(component.getClientId());
            component = findComponentById(baseComponent, "state");
            PrimeFaces.current().ajax().update(component.getClientId());
        }
    }

    public void saveUser() {
        ResponseModel<BloodGroups> bg = abl.getBloodGroupById(bloodGroupId);
        if (bg.status) {
            selectedUser.setBloodGroupId(bg.data);
        } else {
            selectedUser.setBloodGroupId(null);
        }
        ResponseModel<Roles> r = abl.getRoleById(roleId);
        if (r.status) {
            selectedUser.setRoleId(r.data);
        } else {
            selectedUser.setRoleId(null);
        }
        if (selectedUser.getUserId() == null) {
            ResponseModel res = abl.addUser(this.selectedUser);
            if (res.status) {
                successMessage("Add User", "Record added successfully");
            } else {
                errorMessage("Error", res.message);
            }
        } else {
            ResponseModel res = ubl.updateUser(this.selectedUser);
            if (res.status) {
                successMessage("Update User", "Record updated successfully");
            } else {
                errorMessage("Error", res.message);
            }
        }
        PrimeFaces.current().executeScript("PF('manageUserDialog').hide();");
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "userTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public void updateUserProp(Users u) {
        this.selectedUser = u;
        this.bloodGroupId = u.getBloodGroupId().getBloodGroupId();
        this.roleId = u.getRoleId().getRoleid();
        this.Pincode = u.getAddressId().getPincode().getPincode().toString();
        onPincodeSelect();
    }

    public List<BloodGroups> getAllBloodGroups() {
        ResponseModel<Collection<BloodGroups>> res = abl.getAllBloodGroups();
        if (res.status) {
            bloodGroups = res.data;
        }
        return (List<BloodGroups>) bloodGroups;
    }

    public void deleteBloodGroup(int id) {
        ResponseModel res = abl.deleteBloodGroup(id);
        if (res.status == true) {
            successMessage("Delete Blood Group", "Record deleted successfully!");
        } else {
            errorMessage("Error", res.message);
        }
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "bloodGroupTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public void successMessage(String summary, String detail) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
//        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().executeScript("Swal.fire({title: '" + summary + "', text: '" + detail + "', icon: 'success', timer: 1500, showConfirmButton: false});");
    }

    private void errorMessage(String summary, String detail) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
//        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().executeScript("Swal.fire({title : '" + summary + "', text: '" + detail + "', icon: 'error', timer: 1500, showConfirmButton: false});");
    }

    public void openNewBloodGroup() {
        this.selectedBloodGroup = new BloodGroups();
    }

    public void saveBloodGroup() {
        if (selectedBloodGroup.getBloodGroupId() == null) {
            ResponseModel res = abl.addBloodGroup(selectedBloodGroup);
            if (res.status) {
                successMessage("Add Blood Group", "Record added successfully");
            } else {
                errorMessage("Error", res.message);
            }
        } else {
            ResponseModel res = abl.updateBloodGroup(selectedBloodGroup);
            if (res.status) {
                successMessage("Update Blood Group", "Record updated successfully");
            } else {
                errorMessage("Error", res.message);
            }
        }
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "bloodGroupTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
        PrimeFaces.current().executeScript("PF('manageBloodGroupDialog').hide();");
    }

    private UIComponent findComponentById(UIComponent base, String id) {
        if (id.equals(base.getId())) {
            return base;
        } else {
            Iterator<UIComponent> children = base.getFacetsAndChildren();
            while (children.hasNext()) {
                UIComponent child = children.next();
                UIComponent foundComponent = findComponentById(child, id);
                if (foundComponent != null) {
                    return foundComponent;
                }
            }
            return null;
        }
    }

    public List<Degrees> getAllDegrees() {
        ResponseModel<Collection<Degrees>> res = abl.getAllDegrees();
        if (res.status) {
            this.degrees = res.data;
        }
        return (List<Degrees>) degrees;
    }

    public void deleteDegree(int id) {
        ResponseModel res = abl.deleteDegree(id);
        if (res.status == true) {
            successMessage("Delete Degree", "Record deleted successfully!");
        } else {
            errorMessage("Error", res.message);
        }
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "degreeTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public void openNewDegree() {
        this.selectedDegree = new Degrees();
    }

    public void saveDegree() {
        if (selectedDegree.getDegreeId() == null) {
            ResponseModel res = abl.addDegree(selectedDegree);
            if (res.status) {
                successMessage("Add Degree", "Record added successfully");
            } else {
                errorMessage("Error", res.message);
            }
        } else {
            ResponseModel res = abl.updateDegree(selectedDegree);
            if (res.status) {
                successMessage("Update Degree", "Record updated successfully");
            } else {
                errorMessage("Error", res.message);
            }
        }
        PrimeFaces.current().executeScript("PF('manageDegreeDialog').hide();");
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "degreeTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public List<CommonMedications> getAllMedications() {
        ResponseModel<Collection<CommonMedications>> res = abl.getAllCommonMedications();
        if (res.status) {
            this.medications = res.data;
        }
        return (List<CommonMedications>) medications;
    }

    public void deleteMedication(int id) {
        ResponseModel res = abl.deleteCommonMedication(id);
        if (res.status == true) {
            successMessage("Delete Medication", "Record deleted successfully!");
        } else {
            errorMessage("Error", res.message);
        }
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "medicationTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public void openNewMedication() {
        this.selectedMedication = new CommonMedications();
    }

    public void saveMedication() {
        if (selectedMedication.getMedicationId() == null) {
            ResponseModel res = abl.addCommonMedication(selectedMedication);
            if (res.status) {
                successMessage("Add Medication", "Record added successfully");
            } else {
                errorMessage("Error", res.message);
            }
        } else {
            ResponseModel res = abl.updateCommonMedication(selectedMedication);
            if (res.status) {
                successMessage("Update Medication", "Record updated successfully");
            } else {
                errorMessage("Error", res.message);
            }
        }
        PrimeFaces.current().executeScript("PF('manageMedicationDialog').hide();");
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "medicationTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public List<Symptoms> getAllSymptoms() {
        ResponseModel<Collection<Symptoms>> res = abl.getAllSymptoms();
        if (res.status) {
            this.symptoms = res.data;
        }
        return (List<Symptoms>) symptoms;
    }

    public void deleteSymptom(int id) {
        ResponseModel res = abl.deleteSymptom(id);
        if (res.status == true) {
            successMessage("Delete Symptom", "Record deleted successfully!");
        } else {
            errorMessage("Error", res.message);
        }
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "symptomTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public void openNewSymptom() {
        this.selectedSymptom = new Symptoms();
    }

    public void saveSymptom() {
        if (selectedSymptom.getSymptomId() == null) {
            ResponseModel res = abl.addSymptom(selectedSymptom);
            if (res.status) {
                successMessage("Add Symptom", "Record added successfully");
            } else {
                errorMessage("Error", res.message);
            }
        } else {
            ResponseModel res = abl.updateSymptom(selectedSymptom);
            if (res.status) {
                successMessage("Update Symptom", "Record updated successfully");
            } else {
                errorMessage("Error", res.message);
            }
        }
        PrimeFaces.current().executeScript("PF('manageSymptomDialog').hide();");
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "symptomTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

//    public List<Districts> getAllDistricts() {
//        ResponseModel<Collection<Districts>> res = abl.getAllDistricts();
//        if (res.status) {
//            this.districts = res.data;
//        }
//        return (List<Districts>) districts;
//    }
//
//    public void deleteDistrict(int id) {
//        ResponseModel res = abl.deleteDistrict(id);
//        if (res.status == true) {
//            successMessage("Delete District", "Record deleted successfully!");
//        } else {
//            errorMessage("Error", res.message);
//        }
//        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
//        UIComponent datatable = findComponentById(baseComponent, "districtTable");
//        PrimeFaces.current().ajax().update(datatable.getClientId());
//    }
//
//    public void openNewDistrict() {
//        this.selectedDistrict = new Districts();
//    }
//
//    public void saveDistrict() {
//        if (selectedDistrict.getDistrictId() == null) {
//            ResponseModel res = abl.addDistrict(selectedDistrict);
//            if (res.status) {
//                successMessage("Add District", "Record added successfully");
//            } else {
//                errorMessage("Error", res.message);
//            }
//        } else {
//            ResponseModel res = abl.updateDistrict(selectedDistrict);
//            if (res.status) {
//                successMessage("Update District", "Record updated successfully");
//            } else {
//                errorMessage("Error", res.message);
//            }
//        }
//        PrimeFaces.current().executeScript("PF('manageDistrictDialog').hide();");
//        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
//        UIComponent datatable = findComponentById(baseComponent, "districtTable");
//        PrimeFaces.current().ajax().update(datatable.getClientId());
//    }
    public List<Diseases> getAllDiseases() {
        ResponseModel<Collection<Diseases>> res = abl.getAllDiseases();
        if (res.status) {
            this.diseases = res.data;
        }
        return (List<Diseases>) diseases;
    }

    public void deleteDisease(int id) {
        ResponseModel res = abl.deleteDisease(id);
        if (res.status == true) {
            successMessage("Delete Disease", "Record deleted successfully!");
        } else {
            errorMessage("Error", res.message);
        }
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "diseaseTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public void openNewDisease() {
        this.selectedDisease = new Diseases();
    }

    public void saveDisease() {
        if (selectedDisease.getDiseaseId() == null) {
            ResponseModel res = abl.addDisease(selectedDisease);
            if (res.status) {
                successMessage("Add Disease", "Record added successfully");
            } else {
                errorMessage("Error", res.message);
            }
        } else {
            ResponseModel res = abl.updateDisease(selectedDisease);
            if (res.status) {
                successMessage("Update Disease", "Record updated successfully");
            } else {
                errorMessage("Error", res.message);
            }
        }
        PrimeFaces.current().executeScript("PF('manageDiseaseDialog').hide();");
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "diseaseTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public List<FieldOfStudy> getAllFieldOfStudies() {
        ResponseModel<Collection<FieldOfStudy>> res = abl.getAllFieldsofStudy();
        if (true) {
            return (List<FieldOfStudy>) res.data;
        }
        return null;
    }

    public void deleteFieldOfStudy(int id) {
        ResponseModel res = abl.deleteFieldOfStudy(new FieldOfStudy(id));
        if (res.status == true) {
            successMessage("Delete Field Of Study", "Record deleted successfully!");
        } else {
            errorMessage("Error", res.message);
        }
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "fieldOfStudyTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public void openNewFieldOfStudy() {
        this.selectedFieldOfStudy = new FieldOfStudy();
    }

    public void saveFieldOfStudy() {
        if (selectedFieldOfStudy.getFieldId() == null) {
            ResponseModel res = abl.addFieldOfStudy(selectedFieldOfStudy);
            if (res.status) {
                successMessage("Add Field Of Study", "Record added successfully");
            } else {
                errorMessage("Error", res.message);
            }
        } else {
            ResponseModel res = abl.updateFieldOfStudy(selectedFieldOfStudy);
            if (res.status) {
                successMessage("Update Field Of Study", "Record updated successfully");
            } else {
                errorMessage("Error", res.message);
            }
        }
        PrimeFaces.current().executeScript("PF('manageFieldOfStudyDialog').hide();");
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, "fieldOfStudyTable");
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }
}
