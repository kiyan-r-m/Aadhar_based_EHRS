/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIs;

import Beans.AdminBeanLocal;
import Beans.doctorBeanLocal;
import Beans.userBeanLocal;
import Config.Login;
import Entities.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

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
    @Inject
    ExternalContext externalContent;
    Collection<BloodGroups> bloodGroups = new ArrayList<>();
    String Pincode, BloodGroupId, DegreeId, FieldId;
    Collection<String> pc = new ArrayList<>();
    Collection<Pincodes> pincodes = new ArrayList<>();
    Addresses address = new Addresses();
    Collection<FieldOfStudy> fields = new ArrayList<>();
    Collection<Degrees> degrees = new ArrayList<>();
    Collection<Users> accesses = new ArrayList<>();
    Collection<Diseases> diseases = new ArrayList<>();
    Diseases diseaseId = new Diseases();
    Collection<PatientDiseaseMedication> patientDiseaseMedicationCollection = new ArrayList<>();
    Collection<PatientFiles> patientFilesCollection = new ArrayList<>();
    Collection<CommonMedications> medications = new ArrayList<>();
    Collection<MedicalReportCategories> categories = new ArrayList<>();
    Users selectedPatient = new Users();
    Date today = new Date();
    String dropZoneText = "";
    UploadedFile reportFile;
    DoctorNotes DoctorNotes;
    String email;
    int otp;
    Users requestedAccess;
    PatientDoctorMapper myCaseDetails;
    Collection<PatientDoctorMapper> myCases;
    ArrayList<DoctorNotes> myNotes;
    StreamedContent fileDownload;

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
        ResponseModel<Collection<Users>> res5 = dbl.getAllAccessesByDoctorId(doctorDetails.getDoctorId());
        if (res5.status == true) {
            accesses = res5.data;
        }
        ResponseModel<Collection<Diseases>> res6 = abl.getAllDiseases();
        if (res6.status == true) {
            diseases = res6.data;
        }
        this.patientDiseaseMedicationCollection.add(new PatientDiseaseMedication());
        ResponseModel<Collection<CommonMedications>> res7 = abl.getAllCommonMedications();
        if (res7.status == true) {
            medications = res7.data;
        }
        ResponseModel<Collection<MedicalReportCategories>> res8 = dbl.getAllReportCategories();
        if (res8.status) {
            categories = res8.data;
        }
    }

    public DoctorManagedBean() {
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
        if (p != null) {
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

    public void refreshPage(String com) {
        UIComponent baseComponent = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent datatable = findComponentById(baseComponent, com);
        PrimeFaces.current().ajax().update(datatable.getClientId());
    }

    public List<Users> getAllAccesses() {
        ResponseModel<Collection<Users>> r = dbl.getAllAccessesByDoctorId(this.doctorDetails.getDoctorId());
        if (r.status == true) {
            this.accesses = r.data;
        }
        return (List<Users>) this.accesses;
    }

    public String getCurrentDiseaseByUserId(int userId) {
        String disease = "";
        Users u = accesses.stream().filter(a -> a.getUserId().toString().equals(String.valueOf(userId))).findFirst().orElse(null);
        if (u != null) {
            for (PatientDoctorMapper patientDoctorMapper : u.getPatientDoctorMapperCollection()) {
                if (patientDoctorMapper.getDoctorId().getDoctorId().toString().equals(String.valueOf(this.doctorDetails.getDoctorId()))) {
                    if (patientDoctorMapper.getEndDate() == null) {
                        disease = patientDoctorMapper.getDiseaseId().getDiseaseName();
                    }
                }
            }
            if (disease.equals("")) {
                Collection<Date> dates = new ArrayList<>();
                for (PatientDoctorMapper patientDoctorMapper : u.getPatientDoctorMapperCollection()) {
                    if (patientDoctorMapper.getDoctorId().getDoctorId().toString().equals(String.valueOf(this.doctorDetails.getDoctorId()))) {
                        dates.add(patientDoctorMapper.getEndDate());
                    }
                }
                List<java.util.Date> dateList = new LinkedList<java.util.Date>(dates);
                Collections.sort(dateList);
                for (PatientDoctorMapper patientDoctorMapper : u.getPatientDoctorMapperCollection()) {
                    if (patientDoctorMapper.getDoctorId().getDoctorId().toString().equals(String.valueOf(this.doctorDetails.getDoctorId())) && patientDoctorMapper.getEndDate().toLocaleString().compareTo(dateList.get(dateList.size() - 1).toLocaleString()) == 0) {
                        disease = patientDoctorMapper.getDiseaseId().getDiseaseName();
                    }
                }
            }
        }
        return disease;
    }

    public void addMedication() {
        this.patientDiseaseMedicationCollection.add(new PatientDiseaseMedication());
        this.refreshPage("med");
    }

    public void onStartDateSelect() {
        PrimeFaces.current().executeScript("PF('end').getCalendar().setMinDate(PF('start').getCalendar().getDate())");
        this.refreshPage("end");
    }

    public void removeMedication(PatientDiseaseMedication obj) {
        this.patientDiseaseMedicationCollection.remove(obj);
        this.refreshPage("med");
    }

    public void addRep() {
        this.getPatientFilesCollection().add(new PatientFiles());
        this.refreshPage("rec");
    }

    public void removeRep(PatientFiles obj) {
        this.getPatientFilesCollection().remove(obj);
        this.refreshPage("rec");
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            this.reportFile = event.getFile();
            InputStream imageInputStream = this.reportFile.getInputStream();
            String uploadedImageName = this.reportFile.getFileName();

            String ext = uploadedImageName.substring(uploadedImageName.indexOf("."), uploadedImageName.length());
            Random random = new Random();
            String imageName = this.selectedPatient.getUserId() + "_" + System.currentTimeMillis() + ext;
            System.out.println("Image Name: " + imageName);
            PatientFiles pf = (PatientFiles) event.getComponent().getAttributes().get("rf");
            PatientFiles obj = this.patientFilesCollection.stream().filter(pfc -> pfc.equals(pf)).findFirst().orElse(null);
            obj.setLocation(imageName);
            File uploadedImage = new File("D:\\EHR\\Aadhar_based_EHRS\\src\\main\\webapp\\images\\reports\\" + imageName);
            Files.copy(imageInputStream, uploadedImage.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addDisease() {
        PatientDoctorMapper pdm = new PatientDoctorMapper();
        Diseases d = this.diseases.stream().filter(d1 -> d1.getDiseaseId().toString().equals(this.diseaseId.getDiseaseId().toString())).findFirst().orElse(null);
        if (d != null) {
            pdm.setDiseaseId(d);
        } else {
            errorMessage("Add Disease", "Disease not found");
        }
        pdm.setPatientId(selectedPatient);
        pdm.setDoctorId(doctorDetails);
        pdm.setStartDate(new Date());
        pdm.setPatientDiseaseMedicationCollection(this.patientDiseaseMedicationCollection);
        pdm.setPatientFilesCollection(this.patientFilesCollection);
        ResponseModel r = dbl.addPatientDoctorMapperRecord(pdm);
        if (r.status == true) {
            successMessage("Add Disease", "Diseases added successfully");
        } else {
            errorMessage("Add Disease", r.message);
        }
    }

    public void openNewNotes() {
        this.DoctorNotes = new DoctorNotes();
    }

    public void openNewMedication() {
        this.patientDiseaseMedicationCollection = new ArrayList<>();
        this.patientDiseaseMedicationCollection.add(new PatientDiseaseMedication());
    }

    public void openNewReport() {
        this.patientFilesCollection = new ArrayList<>();
        this.patientFilesCollection.add(new PatientFiles());
    }

    public void setProps() {
        int id = getPatientDoctorIdByUserId(this.selectedPatient.getUserId());
        if (id != 0) {
            ResponseModel<Collection<PatientDiseaseMedication>> r = dbl.getMedicationsByPatientDoctorId(id);
            if (r.status == true) {
                this.patientDiseaseMedicationCollection = r.data;
            } else {
                errorMessage("Edit Medication", r.message);
            }
        } else {
            errorMessage("Edit Medication", "Something went wrong");
        }
    }

    public void addNotes() {
        int id = getPatientDoctorIdByUserId(this.selectedPatient.getUserId());
        if (id != 0) {
            this.DoctorNotes.getPatientDoctorMapperId().setPatientDoctorMapperId(id);
            this.DoctorNotes.setCreatedDate(new Date());
            ResponseModel r = dbl.addNotes(this.DoctorNotes);
            if (r.status == true) {
                successMessage("Add Notes", "Notes added successfully");
            } else {
                errorMessage("Add Notes", r.message);
            }
        } else {
            errorMessage("Add Notes", "Something went wrong");
        }
        openDetailsPage(new PatientDoctorMapper(id));
        PrimeFaces.current().executeScript("PF('manageNotesDialog').hide();");
    }

    public void addMedicationToPatientDisease() {
        int id = getPatientDoctorIdByUserId(this.selectedPatient.getUserId());
        if (id != 0) {
            for (PatientDiseaseMedication patientDiseaseMedication : patientDiseaseMedicationCollection) {
                patientDiseaseMedication.getPatientDoctorMapperId().setPatientDoctorMapperId(id);
            }
            ResponseModel r = dbl.addMedication(this.patientDiseaseMedicationCollection);
            if (r.status == true) {
                successMessage("Add Medications", "Medication added successfully");
            } else {
                errorMessage("Add Medications", r.message);
            }
        } else {
            errorMessage("Add Medications", "Something went wrong");
        }
        PrimeFaces.current().executeScript("PF('manageMedicationDialog').hide();");
    }

    public void addReportToPatient() {
        int id = getPatientDoctorIdByUserId(this.selectedPatient.getUserId());
        if (id != 0) {
            for (PatientFiles patientFiles : this.patientFilesCollection) {
                patientFiles.getPatientDoctorMapperId().setPatientDoctorMapperId(id);
            }
            ResponseModel r = dbl.addPatientReports(this.patientFilesCollection);
            if (r.status == true) {
                successMessage("Add Reports", "Reports added successfully");
            } else {
                errorMessage("Add Reports", r.message);
            }
        } else {
            errorMessage("Add Reports", "Something went wrong");
        }
        PrimeFaces.current().executeScript("PF('manageReportDialog').hide();");
    }

    public void editMedication() {
        Boolean s = true;
        String error = "";
        for (PatientDiseaseMedication patientDiseaseMedication : this.patientDiseaseMedicationCollection) {
            ResponseModel r = dbl.updateMedication(patientDiseaseMedication);
            if (r.status == false) {
                s = false;
                error = r.message;
            }
        }
        if (s == true) {
            successMessage("Edit Medication", "Medication updated successfully");
        } else {
            errorMessage("Edit Medication", error);
        }
        PrimeFaces.current().executeScript("PF('manageMedicationDialog1').hide();");
    }

    public int getPatientDoctorIdByUserId(int userId) {
        int patientDoctorId = 0;
        Users u = accesses.stream().filter(a -> a.getUserId().toString().equals(String.valueOf(userId))).findFirst().orElse(null);
        if (u != null) {
            for (PatientDoctorMapper patientDoctorMapper : u.getPatientDoctorMapperCollection()) {
                if (patientDoctorMapper.getDoctorId().getDoctorId().toString().equals(String.valueOf(this.doctorDetails.getDoctorId()))) {
                    if (patientDoctorMapper.getEndDate() == null) {
                        patientDoctorId = patientDoctorMapper.getPatientDoctorMapperId();
                    }
                }
            }
        }
        return patientDoctorId;
    }
    
    public String isCurrent() {
        String disease = "";
        Users u = accesses.stream().filter(a -> a.getUserId().toString().equals(String.valueOf(this.selectedPatient.getUserId()))).findFirst().orElse(null);
        if (u != null) {
            for (PatientDoctorMapper patientDoctorMapper : u.getPatientDoctorMapperCollection()) {
                if (patientDoctorMapper.getDoctorId().getDoctorId().toString().equals(String.valueOf(this.doctorDetails.getDoctorId()))) {
                    if (patientDoctorMapper.getEndDate() == null) {
                        disease = patientDoctorMapper.getDiseaseId().getDiseaseName();
                    }
                }
            }
        }
        return disease;
    }

    public void addAccess() {
        ResponseModel r = dbl.sendOTPForAccess(this.email, (HttpServletRequest) externalContent.getRequest());
        if (r.status == true) {
            ResponseModel<Users> u = ubl.getUserByEmail(email);
            if (u.status) {
                this.requestedAccess = u.data;
                email = null;
            } else {
                errorMessage("Add Access", u.message);
            }
            successMessage("Add Access", "OTP is sent");
            PrimeFaces.current().executeScript("PF('manageAccessDialog').hide();");
            PrimeFaces.current().executeScript("PF('manageAccessDialog1').show();");
        } else {
            errorMessage("Add Access", r.message);
        }
        PrimeFaces.current().executeScript("PF('manageAccessDialog').hide();");
    }

    public void getAccess() {
        ResponseModel r = dbl.giveAccess(this.requestedAccess.getUserId(), this.doctorDetails.getDoctorId(), this.otp, (HttpServletRequest) externalContent.getRequest());
        if (r.status) {
            successMessage("Add Access", "Access added successfully");
        } else {
            errorMessage("Add Access", r.message);
        }
        PrimeFaces.current().executeScript("PF('manageAccessDialog1').hide();");
        refreshPage("AccessesTable");
    }

    public String openDetailsPage(PatientDoctorMapper id) {
        try {
            ResponseModel<PatientDoctorMapper> res = ubl.getPatientDoctorMapperById(id.getPatientDoctorMapperId());
            this.myCaseDetails = res.data;
            return "details.jsf";
        } catch (Exception e) {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int caseid = Integer.parseInt(params.get("caseid"));
            ResponseModel<PatientDoctorMapper> res = ubl.getPatientDoctorMapperById(caseid);
            this.myCaseDetails = res.data;
        }
        return "details.jsf";
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

    public Collection<Users> getAccesses() {
        return accesses;
    }

    public void setAccesses(Collection<Users> accesses) {
        this.accesses = accesses;
    }

    public Users getSelectedPatient() {
        return selectedPatient;
    }

    public void setSelectedPatient(Users selectedPatient) {
        this.selectedPatient = selectedPatient;
    }

    public Collection<Diseases> getDiseases() {
        return diseases;
    }

    public void setDiseases(Collection<Diseases> diseases) {
        this.diseases = diseases;
    }

    public Diseases getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Diseases diseaseId) {
        this.diseaseId = diseaseId;
    }

    public Collection<PatientDiseaseMedication> getPatientDiseaseMedicationCollection() {
        return patientDiseaseMedicationCollection;
    }

    public void setPatientDiseaseMedicationCollection(Collection<PatientDiseaseMedication> patientDiseaseMedicationCollection) {
        this.patientDiseaseMedicationCollection = patientDiseaseMedicationCollection;
    }

    public Collection<CommonMedications> getMedications() {
        return medications;
    }

    public void setMedications(Collection<CommonMedications> medications) {
        this.medications = medications;
    }

    public Collection<PatientFiles> getPatientFilesCollection() {
        return patientFilesCollection;
    }

    public void setPatientFilesCollection(Collection<PatientFiles> patientFilesCollection) {
        this.patientFilesCollection = patientFilesCollection;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Collection<MedicalReportCategories> getCategories() {
        return categories;
    }

    public void setCategories(Collection<MedicalReportCategories> categories) {
        this.categories = categories;
    }

    public String getDropZoneText() {
        return dropZoneText;
    }

    public void setDropZoneText(String dropZoneText) {
        this.dropZoneText = dropZoneText;
    }

    public UploadedFile getReportFile() {
        return reportFile;
    }

    public void setReportFile(UploadedFile reportFile) {
        this.reportFile = reportFile;
    }

    public DoctorNotes getDoctorNotes() {
        return DoctorNotes;
    }

    public void setDoctorNotes(DoctorNotes DoctorNotes) {
        this.DoctorNotes = DoctorNotes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public PatientDoctorMapper getMyCaseDetails() {
        return myCaseDetails;
    }

    public void setMyCaseDetails(PatientDoctorMapper myCaseDetails) {
        this.myCaseDetails = myCaseDetails;
    }

    public Collection<PatientDoctorMapper> getMyCases() {
        Comparator<PatientDoctorMapper> dateCompare = (o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate());
        ResponseModel<Collection<PatientDoctorMapper>> res = new ResponseModel<>();
        res = ubl.getPatientDoctorMapperByUserId(this.selectedPatient.getUserId());
        myCases = res.data;
        ArrayList<PatientDoctorMapper> sortlist = new ArrayList<>(myCases);
        sortlist.sort(Collections.reverseOrder(dateCompare));
        myCases = sortlist;
        return myCases;
    }

    public void setMyCases(Collection<PatientDoctorMapper> myCases) {
        this.myCases = myCases;
    }

    public ArrayList<DoctorNotes> getMyNotes(int id) {
        ResponseModel<ArrayList<DoctorNotes>> res = ubl.getNotesById(id);
        myNotes = res.data;
        return myNotes;
    }

    public void setMyNotes(ArrayList<DoctorNotes> myNotes) {
        this.myNotes = myNotes;
    }

    public StreamedContent getFileDownload(String name) {
        StreamedContent download = new DefaultStreamedContent();
        try {
            File file = new File("D:\\EHR\\Aadhar_based_EHRS\\src\\main\\webapp\\images\\reports\\" + name);
            InputStream input = new FileInputStream(file);
//        download = new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName());
            download = new DefaultStreamedContent().builder()
                    .name(file.getName()).
                    contentType(new MimetypesFileTypeMap().getContentType(file.getName()))
                    .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("D:\\EHR\\Aadhar_based_EHRS\\src\\main\\webapp\\images\\reports\\" + name)).build();
            System.out.println("PREP = " + download.getName());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DoctorManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return download;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }
}
