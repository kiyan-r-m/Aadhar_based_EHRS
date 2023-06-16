/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIs;

import Beans.AdminBeanLocal;
import Beans.doctorBeanLocal;
import Beans.userBeanLocal;
import Config.Login;
import Entities.Addresses;
import Entities.BloodGroups;
import Entities.Degrees;
import Entities.DoctorDetails;
import Entities.FieldOfStudy;
import Entities.Pincodes;
import Entities.ResponseModel;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author admin
 */
@Named(value = "doctorManagedBean")
@SessionScoped
public class DoctorManagedBean implements Serializable {

    @EJB
    doctorBeanLocal dbl;
    @EJB
    userBeanLocal ubl;
    @EJB
    AdminBeanLocal abl;
    DoctorDetails doctorDetails = new DoctorDetails();
    DoctorDetails EditdoctorDetails = new DoctorDetails();
    @Inject
    Login login;
    Collection<BloodGroups> bloodGroups = new ArrayList<>();
    String Pincode, BloodGroupId, DegreeId, FieldId;
    Collection<String> pc = new ArrayList<>();
    Collection<Pincodes> pincodes = new ArrayList<>();
    Addresses address = new Addresses();
    Collection<FieldOfStudy> fields = new ArrayList<>();
    Collection<Degrees> degrees = new ArrayList<>();

    @PostConstruct
    public void init() {
        ResponseModel<Collection<BloodGroups>> res = ubl.getAllBloodGroups();
        if (res.status) {
            bloodGroups = res.data;
        }
        ResponseModel<Collection<Pincodes>> res1 = abl.getAllPincodes();
        if (res1.status) {
            pincodes = res1.data;
            for (Pincodes pincode : pincodes) {
                pc.add(pincode.getPincode().toString());
            }
        }
        ResponseModel<Collection<FieldOfStudy>> res2 = abl.getAllFieldsofStudy();
        if (res2.status) {
            this.fields = res2.data;
        }
        ResponseModel<Collection<Degrees>> res3 = abl.getAllDegrees();
        if (res3.status) {
            this.degrees = res3.data;
        }
        ResponseModel<DoctorDetails> res4 = dbl.getDoctorDetailsByUserId(login.getUserId());
        if (res4.status == true) {
            doctorDetails = res4.data;
        }
    }

    public DoctorManagedBean() {
    }

    public DoctorDetails getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(DoctorDetails doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public DoctorDetails getEditdoctorDetails() {
        return EditdoctorDetails;
    }

    public void setEditdoctorDetails(DoctorDetails EditdoctorDetails) {
        this.EditdoctorDetails = EditdoctorDetails;
    }

    public Collection<BloodGroups> getBloodGroups() {
        return bloodGroups;
    }

    public void setBloodGroups(Collection<BloodGroups> bloodGroups) {
        this.bloodGroups = bloodGroups;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String Pincode) {
        this.Pincode = Pincode;
    }

    public Collection<String> getPc() {
        return pc;
    }

    public void setPc(Collection<String> pc) {
        this.pc = pc;
    }

    public Collection<Pincodes> getPincodes() {
        return pincodes;
    }

    public void setPincodes(Collection<Pincodes> pincodes) {
        this.pincodes = pincodes;
    }

    public Addresses getAddress() {
        return address;
    }

    public void setAddress(Addresses address) {
        this.address = address;
    }

    public Collection<FieldOfStudy> getFields() {
        return fields;
    }

    public void setFields(Collection<FieldOfStudy> fields) {
        this.fields = fields;
    }

    public Collection<Degrees> getDegrees() {
        return degrees;
    }

    public void setDegrees(Collection<Degrees> degrees) {
        this.degrees = degrees;
    }

    public String getBloodGroupId() {
        return BloodGroupId;
    }

    public void setBloodGroupId(String BloodGroupId) {
        this.BloodGroupId = BloodGroupId;
    }

    public String getDegreeId() {
        return DegreeId;
    }

    public void setDegreeId(String DegreeId) {
        this.DegreeId = DegreeId;
    }

    public String getFieldId() {
        return FieldId;
    }

    public void setFieldId(String FieldId) {
        this.FieldId = FieldId;
    }

    public void successMessage(String summary, String detail) {
        PrimeFaces.current().executeScript("Swal.fire({title: '" + summary + "', text: '" + detail + "', icon: 'success', timer: 1500, showConfirmButton: false});");
    }

    private void errorMessage(String summary, String detail) {
        PrimeFaces.current().executeScript("Swal.fire({title : '" + summary + "', text: '" + detail + "', icon: 'error', timer: 1500, showConfirmButton: false});");
    }

    public void showProfile() {
        if (doctorDetails.getDoctorId() == null) {
            ResponseModel<DoctorDetails> res = dbl.getDoctorDetailsByUserId(login.getUserId());
            if (res.status == true) {
                doctorDetails = res.data;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("profile.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(DoctorManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                errorMessage("Profile", res.message);
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("profile.jsf");
            } catch (IOException ex) {
                Logger.getLogger(DoctorManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setProperties() {
        if (this.doctorDetails.getDoctorId() != null) {
            ResponseModel<DoctorDetails> res = dbl.getDoctorDetailsByUserId(login.getUserId());
            if (res.status == true) {
                this.EditdoctorDetails = res.data;
                this.doctorDetails = res.data;
            } else {
                errorMessage("Profile", res.message);
            }
        } else {
            this.EditdoctorDetails = this.doctorDetails;
        }
        this.Pincode = this.EditdoctorDetails.getUserId().getAddressId().getPincode().getPincode().toString();
        this.BloodGroupId = this.EditdoctorDetails.getUserId().getBloodGroupId().getBloodGroupId().toString();
        this.DegreeId = this.EditdoctorDetails.getDegreeId().getDegreeId().toString();
        this.FieldId = this.EditdoctorDetails.getFieldOfStudyId().getFieldId().toString();
        onPincodeSelect();
    }

    public void editProfile() {
        Addresses a = new Addresses();
        Pincodes p = pincodes.stream().filter(p1 -> p1.getPincode().toString().equals(this.Pincode)).findFirst().orElse(null);
        if(p != null) {
            a.setAddress(this.EditdoctorDetails.getUserId().getAddressId().getAddress());
            a.setPincode(p);
            this.EditdoctorDetails.getUserId().setAddressId(a);
        }
        BloodGroups bg = bloodGroups.stream().filter(bg1 -> bg1.getBloodGroupId().toString().equals(this.BloodGroupId)).findFirst().orElse(null);
        if (bg != null) {
            this.EditdoctorDetails.getUserId().setBloodGroupId(bg);
        }
        Degrees d = degrees.stream().filter(d1 -> d1.getDegreeId().toString().equals(this.DegreeId)).findFirst().orElse(null);
        if (d != null) {
            this.EditdoctorDetails.setDegreeId(d);
        }
        FieldOfStudy fs = fields.stream().filter(fs1 -> fs1.getFieldId().toString().equals(this.FieldId)).findFirst().orElse(null);
        if (fs != null) {
            this.EditdoctorDetails.setFieldOfStudyId(fs);
        }
        ResponseModel r = ubl.updateUser(this.EditdoctorDetails.getUserId());
        if (r.status == true) {
            r = dbl.updateDoctorDetails(this.EditdoctorDetails);
            if (r.status == true) {
                PrimeFaces.current().executeScript("PF('manageDoctorDialog').hide();");
                showProfile();
                successMessage("Profile Update", "Profile updated successfully!");
            } else {
                PrimeFaces.current().executeScript("PF('manageDoctorDialog').hide();");
                errorMessage("Profile Update", r.message);
            }
        } else {
            PrimeFaces.current().executeScript("PF('manageDoctorDialog').hide();");
            errorMessage("Profile Update", r.message);
        }
    }
    
    public void onPincodeSelect() {
        if (Pincode != null || Pincode.isEmpty()) {
            Pincodes localP = pincodes.stream().filter(p -> p.getPincode().toString().equals(Pincode)).findFirst().orElse(null);
            if (EditdoctorDetails == null) {
                address.setPincode(localP);
            } else {
                this.EditdoctorDetails.getUserId().getAddressId().setPincode(localP);
            }
            UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent component = findComponentById(baseComponent, "district");
            PrimeFaces.current().ajax().update(component.getClientId());
            component = findComponentById(baseComponent, "state");
            PrimeFaces.current().ajax().update(component.getClientId());
        } else {
            this.EditdoctorDetails.getUserId().getAddressId().setPincode(new Pincodes());
            UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent component = findComponentById(baseComponent, "district");
            PrimeFaces.current().ajax().update(component.getClientId());
            component = findComponentById(baseComponent, "state");
            PrimeFaces.current().ajax().update(component.getClientId());
        }
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
}
