/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import Entities.Allergies;
import Entities.Appointments;
import Entities.DoctorDetails;
import Entities.PatientDoctorMapper;
import Entities.ResponseModel;
import Entities.Users;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author krdmo
 */
@Local
public interface doctorBeanLocal {
    ResponseModel addDoctorDetails(DoctorDetails data);
    ResponseModel<Collection<DoctorDetails>> getDoctorDetails(int doctorId);
    ResponseModel updateDoctorDetails(DoctorDetails data);
    ResponseModel addAppointment(Appointments data);
    ResponseModel getAppointmentsByDoctorId(int id);
    ResponseModel updateAppointment(Appointments data);
    ResponseModel deleteAppointment(Appointments data);
    ResponseModel addPatientDoctorMapperRecord(PatientDoctorMapper data);
    ResponseModel getPatientDoctorMapperRecordByDoctorId(int id);
    ResponseModel updatePatientDoctorMapperRecord(PatientDoctorMapper data);
    ResponseModel getAllAccessesByDoctorId(int id);
    ResponseModel addPatientAccess(int id, Users user);
//    ResponseModel deletePatientAccess(Users data);
//    ResponseModel getAllergyByPatient(Users data);
//    ResponseModel addNewAllergyToPatient(Allergies allergy, Users user);
//    ResponseModel deleteAllergyFromPatient(Allergies allergy, Users user);
    
    //ResponseModel addAccess(PatientAccessMapper data);
    //ResponseModel<Collection<PatientAccessMapper>> getAccessInfo();
    //ResponseModel updateAccess();
    //ResponseModel addPatientAllergy(PatientAllergyMapper data);
    //ResponseModel<Collection<PatientAllergyMapper>> getPatientAllergy();
    //ResponseModel updatePatientAllergy();
    //ResponseModel removePatientAllergy(PatientAllergyMapper data);
    //ResponseModel addPatientChronicDisease(PatientChronicMapper data);
    //ResponseModel<Collection<PatientChronicMapper>> getPatientChronicDiseases();
    //ResponseModel updatePatientChronicDisease();
    //ResponseModel removePatientChronicDisease(PatientChronicMapper data); 
}
