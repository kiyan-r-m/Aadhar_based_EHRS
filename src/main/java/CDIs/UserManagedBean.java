/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIs;

import Beans.userBeanLocal;
import Config.Login;
import Entities.DoctorDetails;
import Entities.DoctorNotes;
import Entities.PatientDoctorMapper;
import Entities.ResponseModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author krdmo
 */
@Named(value = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    @EJB
    userBeanLocal ubl;
    Collection<PatientDoctorMapper> myCases;
    Collection<DoctorDetails> myAccess;
    ArrayList<DoctorNotes> myNotes;
    PatientDoctorMapper myCaseDetails;
    StreamedContent fileDownload;

    @Inject
    Login login;

    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
    }

    public Collection<PatientDoctorMapper> getMyCases() {
        Comparator<PatientDoctorMapper> dateCompare = (o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate());
        ResponseModel<Collection<PatientDoctorMapper>> res = new ResponseModel<>();
        res = ubl.getPatientDoctorMapperByUserId(login.getUserId());
        myCases = res.data;
        ArrayList<PatientDoctorMapper> sortlist = new ArrayList<>(myCases);
        sortlist.sort(Collections.reverseOrder(dateCompare));
        myCases = sortlist;
        return myCases;
    }

    public void setMyCases(ArrayList<PatientDoctorMapper> myCases) {
        this.myCases = myCases;
    }

    public Collection<DoctorDetails> getMyAccess() {
        ResponseModel<Collection<DoctorDetails>> res = ubl.getAccessMapperByUserId(login.getUserId());
        myAccess = res.data;
        return myAccess;
    }

    public void setMyAccess(Collection<DoctorDetails> myAccess) {
        this.myAccess = myAccess;
    }

    public void deleteAccess(int doctorId) {
        Collection<Integer> dids = new ArrayList<>();
        dids.add(doctorId);
        ResponseModel res = ubl.removeAccessMapperToUser(login.getUserId(), dids);
    }

    public ArrayList<DoctorNotes> getMyNotes(int id) {
        ResponseModel<ArrayList<DoctorNotes>> res = ubl.getNotesById(id);
        myNotes = res.data;
        return myNotes;
    }

    public void setMyNotes(ArrayList<DoctorNotes> myNotes) {
        this.myNotes = myNotes;

    }

    public PatientDoctorMapper getMyCaseDetails() {
        return myCaseDetails;
    }

    public void setMyCaseDetails(PatientDoctorMapper myCaseDetails) {
        this.myCaseDetails = myCaseDetails;
    }

    public String openDetailsPage(PatientDoctorMapper id) {

        try {
            ResponseModel<PatientDoctorMapper> res = ubl.getPatientDoctorMapperById(id.getPatientDoctorMapperId());
            this.myCaseDetails = res.data;
            return "details.jsf";
        }
        catch(Exception e)
        {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int caseid = Integer.parseInt(params.get("caseid"));
            ResponseModel<PatientDoctorMapper> res = ubl.getPatientDoctorMapperById(caseid);
            this.myCaseDetails = res.data;
        }
        return "details.jsf";
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
