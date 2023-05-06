/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import Entities.BloodGroups;
import Entities.CommonMedications;
import Entities.Degrees;
import Entities.Diseases;
import Entities.Districts;
import Entities.ResponseModel;
import Entities.Symptoms;
import java.util.Collection;
import javax.ejb.Local;
import Entities.Users;
import Entities.FieldOfStudy;
import Entities.Pincodes;
import Entities.States;

/**
 *
 * @author admin
 */
@Local
public interface AdminBeanLocal {

    ResponseModel<Collection<BloodGroups>> getAllBloodGroups();

    ResponseModel addBloodGroup(BloodGroups bg);

    ResponseModel updateBloodGroup(BloodGroups bg);

    ResponseModel deleteBloodGroup(int id);
    
    ResponseModel<Collection<Degrees>> getAllDegrees();

    ResponseModel addDegree(Degrees d);

    ResponseModel updateDegree(Degrees d);

    ResponseModel deleteDegree(int id);
    
    ResponseModel<Collection<Districts>> getAllDistricts();

    ResponseModel addDistrict(Districts d);

    ResponseModel updateDistrict(Districts d);

    ResponseModel deleteDistrict(int id);
    
    ResponseModel<Collection<Symptoms>> getAllSymptoms();

    ResponseModel addSymptom(Symptoms s);

    ResponseModel updateSymptom(Symptoms s);

    ResponseModel deleteSymptom(int id);
    
    ResponseModel<Symptoms> getSymptomById(int id);
    
    ResponseModel<Collection<CommonMedications>> getAllCommonMedications();

    ResponseModel addCommonMedication(CommonMedications cm);

    ResponseModel updateCommonMedication(CommonMedications cm);

    ResponseModel deleteCommonMedication(int id);

    ResponseModel<CommonMedications> getCommonMedicationById(int id);
    
    ResponseModel<Collection<Diseases>> getAllDiseases();

    ResponseModel addDisease(Diseases d);

    ResponseModel updateDisease(Diseases d);

    ResponseModel deleteDisease(int id);    

    ResponseModel addFieldOfStudy(FieldOfStudy data);
    ResponseModel getAllFieldsofStudy();
    ResponseModel getFieldOfStudy(int id);
    ResponseModel updateFieldOfStudy(FieldOfStudy data);
    ResponseModel deleteFieldOfStudy(FieldOfStudy data);
    ResponseModel addMedication(CommonMedications data);
    ResponseModel getAllMedications();
    ResponseModel getMedication(int id);
    ResponseModel updateMedication(CommonMedications data);
    ResponseModel deleteMedication(CommonMedications data);
    ResponseModel addPincode (Pincodes data);
    ResponseModel getAllPincodes();
    ResponseModel getPincode (int pin);
    ResponseModel updatePincode(Pincodes data);
    ResponseModel deletePincode(Pincodes data);
    ResponseModel addState (States data);
    ResponseModel getAllStates();
    ResponseModel getState (int id);
    ResponseModel updateState(States data);
    ResponseModel deleteState(States data);
    ResponseModel getSymptom (int id);
    ResponseModel deleteSymptom(Symptoms data);
    ResponseModel addAdminUser (Users data);
    ResponseModel getAllAdmins();
    ResponseModel getAdmin (int id);
    ResponseModel updateAdminUser(Users data);
    ResponseModel deleteAdminUser(Users data);
}

