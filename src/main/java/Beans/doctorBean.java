/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entities.Appointments;
import Entities.DoctorDetails;
import Entities.DoctorNotes;
import Entities.MedicalReportCategories;
import Entities.PatientDiseaseMedication;
import Entities.PatientDoctorMapper;
import Entities.PatientFiles;
import Entities.ResponseModel;
import Entities.Users;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author krdmo
 */
@Stateless
public class doctorBean implements doctorBeanLocal {

    @PersistenceContext(unitName = "my_persistence")
    EntityManager em;

    @Override
    public ResponseModel addDoctorDetails(DoctorDetails data) {
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
    public ResponseModel<Collection<DoctorDetails>> getDoctorDetails(int doctorId) {
        ResponseModel<Collection<DoctorDetails>> res = new ResponseModel<>();
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
    public ResponseModel updateDoctorDetails(DoctorDetails data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(DoctorDetails.class, data.getDoctorId()) != null) {
                DoctorDetails doctor = em.find(DoctorDetails.class, data.getDoctorId());
                
                doctor.setDegreeId(data.getDegreeId());
                doctor.setEducationLevelId(data.getEducationLevelId());
                doctor.setFieldOfStudyId(data.getFieldOfStudyId());
                doctor.setLicenceNo(data.getLicenceNo());
                
                em.merge(doctor);
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

   
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public ResponseModel addAppointment(Appointments data) {
        ResponseModel res = new ResponseModel();
        Appointments addData = em.find(Appointments.class, data.getAppointmentId());
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
    public ResponseModel getAppointmentsByDoctorId(int id) {
        ResponseModel res = new ResponseModel();
        DoctorDetails doctor = em.find(DoctorDetails.class, id);
        Collection<Appointments> appointments = doctor.getAppointmentsCollection();
        if(!appointments.isEmpty())
        {
            res.status = true;
            res.data = appointments;
            res.message = "Get success";
            
        }
        else
        {
            res.status = false;
            res.message = "Invalid input";
            
        }
        return res;
    }

    @Override
    public ResponseModel updateAppointment(Appointments data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(Appointments.class, data.getAppointmentId()) != null) {
                Appointments appointment = em.find(Appointments.class, data.getAppointmentId());
                
                appointment.setAppointmentDate(data.getAppointmentDate());
                appointment.setDuration(data.getDuration());
                appointment.setIsAttended(data.getIsAttended());
                
                
                em.merge(appointment);
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
    public ResponseModel deleteAppointment(Appointments data) {
        ResponseModel res = new ResponseModel();
        Appointments deletedata = em.find(Appointments.class, data.getAppointmentId());
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
    public ResponseModel addPatientDoctorMapperRecord(PatientDoctorMapper data) {
        ResponseModel res = new ResponseModel();
        Collection<PatientDoctorMapper> addData = em.createNamedQuery("PatientDoctorMapper.findByPatientIdDoctorIdDiseaseIdStartDate").setParameter("patientId", data.getPatientId().getUserId()).setParameter("doctorId", data.getDoctorId().getDoctorId()).setParameter("diseaseId", data.getDiseaseId().getDiseaseId()).setParameter("startDate", data.getStartDate()).getResultList();
        try {
            if (data == null) {
                res.status = false;
                res.message = "input invalid";
                return res;
            } else {
                if(addData.isEmpty())
                {
                    PatientDoctorMapper pdm = new PatientDoctorMapper();
                    pdm.setStartDate(new Date());
                    pdm.setPatientId(data.getPatientId());
                    pdm.setDiseaseId(data.getDiseaseId());
                    pdm.setDoctorId(data.getDoctorId());
                    em.persist(pdm);
                    em.flush();
                    for (PatientDiseaseMedication patientDiseaseMedication : data.getPatientDiseaseMedicationCollection()) {
                        patientDiseaseMedication.setPatientDoctorMapperId(pdm);
                    }
                    pdm.setPatientDiseaseMedicationCollection(data.getPatientDiseaseMedicationCollection());
                    for (PatientFiles patientFiles : data.getPatientFilesCollection()) {
                        patientFiles.setPatientDoctorMapperId(pdm);
                    }
                    pdm.setPatientFilesCollection(data.getPatientFilesCollection());
                    em.merge(pdm);
                    res.status = true;
                }
            }
        } catch (Exception e) {
            res.message = e.getMessage();
            res.status = false;
        }
        return res;
    }

    @Override
    public ResponseModel getPatientDoctorMapperRecordByDoctorId(int id) {
        ResponseModel<Collection<PatientDoctorMapper>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("PatientDoctorMapper.findByDoctorId").setParameter("doctorId", id).getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updatePatientDoctorMapperRecord(PatientDoctorMapper data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(PatientDoctorMapper.class, data.getPatientDoctorMapperId()) != null) {
                PatientDoctorMapper updateData = em.find(PatientDoctorMapper.class, data.getPatientDoctorMapperId());
                
                updateData.setDiseaseId(data.getDiseaseId());
                updateData.setStartDate(data.getStartDate());
                updateData.setEndDate(data.getEndDate());
                
                em.merge(updateData);
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
    public ResponseModel<Collection<Users>> getAllAccessesByDoctorId(int id) {
        ResponseModel res = new ResponseModel();
        DoctorDetails data = em.find(DoctorDetails.class, id);
        Collection<Users> users = data.getUsersCollection();
        if(!users.isEmpty())
        {
            res.status = true;
            res.data = users;
            res.message = "Get Success";
        }
        else
        {
            res.status = false;
            res.message = "Invalid input";
        }
        return res;
    }

    @Override
    public ResponseModel addPatientAccess(int id, Users user) {
        ResponseModel res = new ResponseModel();
        DoctorDetails doctor = em.find(DoctorDetails.class, id);
        Users userdata = em.find(Users.class, user.getUserId());
        Collection<Users> users = doctor.getUsersCollection();
        if(users.contains(user))
        {
            res.status = false;
            res.message = "user already exists";
        }
        else
        {
            if(userdata!=null)
            {
                userdata.getDoctorDetailsCollection().add(doctor);
                users.add(user);
                res.status = true;
            }
            else
            {
                res.status = false;
                res.message = "invalid user";
            }
        }
        return res;
    }

//    @Override
//    public ResponseModel deletePatientAccess(Users data) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ResponseModel getAllergyByPatient(Users data) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ResponseModel addNewAllergyToPatient(Allergies allergy, Users user) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ResponseModel deleteAllergyFromPatient(Allergies allergy, Users user) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public ResponseModel<DoctorDetails> getDoctorDetailsByUserId(int id) {
        ResponseModel<DoctorDetails> res = new ResponseModel<>();
        try {
            if (String.valueOf(id).isEmpty() || id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            res.data = (DoctorDetails) em.createNamedQuery("DoctorDetails.findByUserId").setParameter("userId", id).getSingleResult();
            if (res.data != null) {
                res.status = true;
                return res;
            } else {
                res.status = false;
                res.message = "Doctor not found";
                return res;
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
            return res;
        }
    }

    @Override
    public ResponseModel<Collection<MedicalReportCategories>> getAllReportCategories() {
        ResponseModel<Collection<MedicalReportCategories>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("MedicalReportCategories.findAll").getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addNotes(DoctorNotes data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (data.getPatientDoctorMapperId().getPatientDoctorMapperId() != 0) {
                PatientDoctorMapper pdm = em.find(PatientDoctorMapper.class, data.getPatientDoctorMapperId().getPatientDoctorMapperId());
                if (pdm != null) {
                    data.setPatientDoctorMapperId(pdm);
                    em.persist(data);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "Something went wrong";
                }
            } else {
                res.status = false;
                res.message = "Something went wrong";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addMedication(Collection<PatientDiseaseMedication> data) {
        ResponseModel res = new ResponseModel();
        PatientDoctorMapper pdm = em.find(PatientDoctorMapper.class, data.iterator().next().getPatientDoctorMapperId().getPatientDoctorMapperId());
        try {
            if (data == null) {
                res.status = false;
                res.message = "input invalid";
                return res;
            } else {
                if(pdm != null)
                {
                    for (PatientDiseaseMedication patientDiseaseMedication : data) {
                        patientDiseaseMedication.setPatientDoctorMapperId(pdm);
                    }
                    pdm.setPatientDiseaseMedicationCollection(data);
                    em.merge(pdm);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "Disease access not found";
                }
            }
        } catch (Exception e) {
            res.message = e.getMessage();
            res.status = false;
        }
        return res;
    }

    @Override
    public ResponseModel addPatientReports(Collection<PatientFiles> data) {
        ResponseModel res = new ResponseModel();
        PatientDoctorMapper pdm = em.find(PatientDoctorMapper.class, data.iterator().next().getPatientDoctorMapperId().getPatientDoctorMapperId());
        try {
            if (data == null) {
                res.status = false;
                res.message = "input invalid";
                return res;
            } else {
                if(pdm != null)
                {
                    for (PatientFiles patientFiles : data) {
                        patientFiles.setPatientDoctorMapperId(pdm);
                    }
                    pdm.setPatientFilesCollection(data);
                    em.merge(pdm);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "Disease access not found";
                }
            }
        } catch (Exception e) {
            res.message = e.getMessage();
            res.status = false;
        }
        return res;
    }

    @Override
    public ResponseModel updateMedication(PatientDiseaseMedication data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(PatientDiseaseMedication.class, data.getPatientDiseaseMedicationId())!= null) {
                PatientDiseaseMedication pdm = em.find(PatientDiseaseMedication.class, data.getPatientDiseaseMedicationId());
                pdm.setStartDate(data.getStartDate());
                pdm.setEndDate(data.getEndDate());
                pdm.setFrequency(data.getFrequency());
                em.merge(pdm);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Medication not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Collection<PatientDiseaseMedication>> getMedicationsByPatientDoctorId(int id) {
        ResponseModel<Collection<PatientDiseaseMedication>> res = new ResponseModel<>();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(PatientDoctorMapper.class, id)!= null) {
                PatientDoctorMapper pdm = em.find(PatientDoctorMapper.class, id);
                res.data = pdm.getPatientDiseaseMedicationCollection();
                res.status = true;
            } else {
                res.status = false;
                res.message = "Medication not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }
}
