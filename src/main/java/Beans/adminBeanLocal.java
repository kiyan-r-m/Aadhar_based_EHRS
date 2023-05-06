/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import Entities.CommonMedications;
import Entities.FieldOfStudy;
import Entities.Pincodes;
import Entities.ResponseModel;
import Entities.States;
import Entities.Symptoms;
import Entities.Users;
import javax.ejb.Local;

/**
 *
 * @author krdmo
 */
@Local
public interface adminBeanLocal {
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
    ResponseModel addSymptom (Symptoms data);
    ResponseModel getAllSymptoms();
    ResponseModel getSymptom (int id);
    ResponseModel updateSymptom(Symptoms data);
    ResponseModel deleteSymptom(Symptoms data);
    ResponseModel addAdminUser (Users data);
    ResponseModel getAllAdmins();
    ResponseModel getAdmin (int id);
    ResponseModel updateAdminUser(Users data);
    ResponseModel deleteAdminUser(Users data);
    
}
