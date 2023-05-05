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
}
