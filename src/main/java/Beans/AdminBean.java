/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entities.CommonMedications;
import Entities.FieldOfStudy;
import Entities.Pincodes;
import Entities.ResponseModel;
import Entities.States;
import Entities.Symptoms;
import Entities.Users;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author krdmo
 */
@Stateless
public class adminBean implements adminBeanLocal {

    @PersistenceContext(unitName="my_persistence")
    EntityManager em;
    
    @Override
    public ResponseModel addFieldOfStudy(FieldOfStudy data) {
        ResponseModel res = new ResponseModel();
        FieldOfStudy addData = em.find(FieldOfStudy.class, data.getFieldId());
        try {
            if (data == null) {
                res.status = false;
                res.message = "input invalid";
                return res;
            } else {
                if(addData == null)
                {
                    em.persist(data);
                    res.status = true;
                    res.message = "input success";
                }
            }
        } catch (Exception e) {
            res.message = e.getMessage();
            res.status = false;
        }
        return res;
    }

    @Override
    public ResponseModel getAllFieldsofStudy() {
        ResponseModel<Collection<FieldOfStudy>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("FieldOfStudy.findAll").getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel getFieldOfStudy(int id) {
        ResponseModel res = new ResponseModel();
        res.data = em.find(FieldOfStudy.class, id);
        res.status = res.data!=null;
        return res;
        
    }

    @Override
    public ResponseModel updateFieldOfStudy(FieldOfStudy data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(FieldOfStudy.class, data.getFieldId()) != null) {
                FieldOfStudy field = em.find(FieldOfStudy.class, data.getFieldId());
                
                field.setFieldName(data.getFieldName());
                em.merge(field);
                res.status = true;
            } else {
                res.status = false;
                res.message = "User not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel deleteFieldOfStudy(FieldOfStudy data) {
        ResponseModel res = new ResponseModel();
        FieldOfStudy deletedata = em.find(FieldOfStudy.class, data.getFieldId());
        try{
            if(deletedata == null){
                res.status = false;
                res.message = "input invalid";
            }
            else{
                
                em.remove(deletedata);
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
    public ResponseModel addMedication(CommonMedications data) {
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
    public ResponseModel getAllMedications() {
        ResponseModel<Collection<CommonMedications>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("CommonMedications.findAll").getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel getMedication(int id) {
        ResponseModel res = new ResponseModel();
        res.data = em.find(CommonMedications.class, id);
        res.status = res.data!=null;
        return res;
    }

    @Override
    public ResponseModel updateMedication(CommonMedications data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(CommonMedications.class, data.getMedicationId()) != null) {
                CommonMedications medication = em.find(CommonMedications.class, data.getMedicationId());
                
                medication.setMedicationName(data.getMedicationName());
                em.merge(medication);
                res.status = true;
            } else {
                res.status = false;
                res.message = "User not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel deleteMedication(CommonMedications data) {
        ResponseModel res = new ResponseModel();
        CommonMedications deletedata = em.find(CommonMedications.class, data.getMedicationId());
        try{
            if(deletedata == null){
                res.status = false;
                res.message = "input invalid";
            }
            else{
                
                em.remove(deletedata);
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
    public ResponseModel addPincode(Pincodes data) {
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
    public ResponseModel getAllPincodes() {
        ResponseModel<Collection<Pincodes>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("Pincodes.findAll").getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel getPincode(int pin) {
        ResponseModel res = new ResponseModel();
        res.data = em.find(Pincodes.class, pin);
        res.status = res.data!=null;
        return res;
    }

    @Override
    public ResponseModel updatePincode(Pincodes data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(Pincodes.class, data.getPincode()) != null) {
                Pincodes pin = em.find(Pincodes.class, data.getPincode());
                pin.setDistrictId(data.getDistrictId());
                pin.setStateId(data.getStateId());
                em.merge(pin);
                res.status = true;
            } else {
                res.status = false;
                res.message = "User not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel deletePincode(Pincodes data) {
        ResponseModel res = new ResponseModel();
        Pincodes deletedata = em.find(Pincodes.class, data.getPincode());
        try{
            if(deletedata == null){
                res.status = false;
                res.message = "input invalid";
            }
            else{
                
                em.remove(deletedata);
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
    public ResponseModel addState(States data) {
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
    public ResponseModel getAllStates() {
        ResponseModel<Collection<States>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("States.findAll").getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel getState(int id) {
        ResponseModel res = new ResponseModel();
        res.data = em.find(States.class, id);
        res.status = res.data!=null;
        return res;
    }

    @Override
    public ResponseModel updateState(States data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(States.class, data.getStateId()) != null) {
                States state = em.find(States.class, data.getStateId());
                state.setStateName(data.getStateName());
               
                em.merge(state);
                res.status = true;
            } else {
                res.status = false;
                res.message = "User not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel deleteState(States data) {
        ResponseModel res = new ResponseModel();
        States deletedata = em.find(States.class, data.getStateId());
        try{
            if(deletedata == null){
                res.status = false;
                res.message = "input invalid";
            }
            else{
                
                em.remove(deletedata);
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
    public ResponseModel addSymptom(Symptoms data) {
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
    public ResponseModel getAllSymptoms() {
        ResponseModel<Collection<Symptoms>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("Symptoms.findAll").getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel getSymptom(int id) {
        ResponseModel res = new ResponseModel();
        res.data = em.find(Symptoms.class, id);
        res.status = res.data!=null;
        return res;
    }

    @Override
    public ResponseModel updateSymptom(Symptoms data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(Symptoms.class, data.getSymptomId()) != null) {
                Symptoms symptom = em.find(Symptoms.class, data.getSymptomId());
                symptom.setSymptomName(data.getSymptomName());
                em.merge(symptom);
                res.status = true;
            } else {
                res.status = false;
                res.message = "User not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

     @Override
    public ResponseModel deleteSymptom(Symptoms data) {
        ResponseModel res = new ResponseModel();
        Symptoms deletedata = em.find(Symptoms.class, data.getSymptomId());
        try{
            if(deletedata == null){
                res.status = false;
                res.message = "input invalid";
            }
            else{
                
                em.remove(deletedata);
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
    public ResponseModel addAdminUser(Users data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseModel getAllAdmins() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseModel getAdmin(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseModel updateAdminUser(Users data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseModel deleteAdminUser(Users data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    
}


























