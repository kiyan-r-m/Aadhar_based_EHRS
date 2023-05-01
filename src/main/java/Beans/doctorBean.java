/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import com.mycompany.aadhar_based_ehrs.*;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author krdmo
 */
@Stateless
public class doctorBean implements doctorBeanLocal {

    @PersistenceContext(unitName = "ehrJPU")
    EntityManager em;

    @Override
    public ResponseModel addDoctorDetails(Doctordetails data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "input invalid";
                return res;
            } else {
                em.persist(data);
                res.status = true;
                res.message = "input success";
            }
        } catch (Exception e) {
            res.message = e.getMessage();
            res.status = false;
        }
        return res;
    }

    @Override
    public ResponseModel<Collection<Doctordetails>> getDoctorDetails(int doctorId) {
        ResponseModel<Collection<Doctordetails>> res = new ResponseModel<>();
        try{
            res.data = em.createNamedQuery("Doctordetails.findAll").getResultList();
            res.status = true;
            
        }
        
        catch(Exception e){
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updateDoctorDetails(int doctorId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseModel addAccess(PatientAccessMapper data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "input invalid";
                return res;
            } else {
                em.persist(data);
                res.status = true;
                res.message = "input success";
            }
        } catch (Exception e) {
            res.message = e.getMessage();
            res.status = false;
        }
        return res;
    }

    @Override
    public ResponseModel<Collection<PatientAccessMapper>> getAccessInfo() {
        ResponseModel<Collection<PatientAccessMapper>> res = new ResponseModel<>();
        try{
            res.data = em.createNamedQuery("PatientAccessMapper.findAll").getResultList();
            res.status = true;
            
        }
        
        catch(Exception e){
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updateAccess() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseModel addPatientAllergy(PatientAllergyMapper data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "input invalid";
                return res;
            } else {
                em.persist(data);
                res.status = true;
                res.message = "input success";
            }
        } catch (Exception e) {
            res.message = e.getMessage();
            res.status = false;
        }
        return res;
    }

    @Override
    public ResponseModel<Collection<PatientAllergyMapper>> getPatientAllergy() {
        ResponseModel<Collection<PatientAllergyMapper>> res = new ResponseModel<>();
        try{
            res.data = em.createNamedQuery("PatientAllergyMapper.findAll").getResultList();
            res.status = true;
            
        }
        
        catch(Exception e){
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updatePatientAllergy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseModel removePatientAllergy(PatientAllergyMapper data) {
        ResponseModel res = new ResponseModel();
        try{
            if(data==null){
                res.status = false;
                res.message = "input invalid";
            }
            else{
                em.remove(data);
                res.status = true;
                res.message = "Delete success";
            }
        }
        catch(Exception e){
            res.message = e.getMessage();
            res.status = false;
        }
        return res;
    }

    @Override
    public ResponseModel addPatientChronicDisease(PatientChronicMapper data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "input invalid";
                return res;
            } else {
                em.persist(data);
                res.status = true;
                res.message = "input success";
            }
        } catch (Exception e) {
            res.message = e.getMessage();
            res.status = false;
        }
        return res;
    }

    @Override
    public ResponseModel<Collection<PatientChronicMapper>> getPatientChronicDiseases() {
        ResponseModel<Collection<PatientChronicMapper>> res = new ResponseModel<>();
        try{
            res.data = em.createNamedQuery("PatientChronicMapper.findAll").getResultList();
            res.status = true;
            
        }
        
        catch(Exception e){
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updatePatientChronicDisease() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseModel removePatientChronicDisease(PatientChronicMapper data) {
        ResponseModel res = new ResponseModel();
        try{
            if(data==null){
                res.status = false;
                res.message = "input invalid";
            }
            else{
                em.remove(data);
                res.status = true;
                res.message = "Delete success";
            }
        }
        catch(Exception e){
            res.message = e.getMessage();
            res.status = false;
        }
        return res;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
