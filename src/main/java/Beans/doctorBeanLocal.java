/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import com.mycompany.aadhar_based_ehrs.*;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author krdmo
 */
@Local
public interface doctorBeanLocal {
    ResponseModel addDoctorDetails(Doctordetails data);
    ResponseModel<Collection<Doctordetails>> getDoctorDetails(int doctorId);
    ResponseModel updateDoctorDetails(int doctorId);
    ResponseModel addAccess(PatientAccessMapper data);
    ResponseModel<Collection<PatientAccessMapper>> getAccessInfo();
    ResponseModel updateAccess();
    ResponseModel addPatientAllergy(PatientAllergyMapper data);
    ResponseModel<Collection<PatientAllergyMapper>> getPatientAllergy();
    ResponseModel updatePatientAllergy();
    ResponseModel removePatientAllergy(PatientAllergyMapper data);
    ResponseModel addPatientChronicDisease(PatientChronicMapper data);
    ResponseModel<Collection<PatientChronicMapper>> getPatientChronicDiseases();
    ResponseModel updatePatientChronicDisease();
    ResponseModel removePatientChronicDisease(PatientChronicMapper data); 
}
