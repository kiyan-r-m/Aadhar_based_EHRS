/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entities.*;
import ReportModels.BloodGroupFrequency;
import ReportModels.DiseaseToFrequency;
import ReportModels.DateWiseCaseFrequency;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author admin
 */
@Stateless
public class AdminBean implements AdminBeanLocal {

    @PersistenceContext(unitName = "my_persistence")
    private EntityManager em;

    @Inject
    private Pbkdf2PasswordHash PasswordHash;

    @EJB
    EmailClientLocal mail;

    @Override
    public ResponseModel<Collection<BloodGroups>> getAllBloodGroups() {
        ResponseModel<Collection<BloodGroups>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("BloodGroups.findAll").getResultList();
            res.status = true;
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addBloodGroup(BloodGroups bg) {
        ResponseModel res = new ResponseModel();
        try {
            if (bg == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("BloodGroups.findByBloodGroupName").setParameter("bloodGroupName", bg.getBloodGroupName()).getResultList().isEmpty()) {
                em.persist(bg);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Blood Group already exists";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updateBloodGroup(BloodGroups bg) {
        ResponseModel res = new ResponseModel();
        try {
            if (bg == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("BloodGroups.findByBloodGroupName").setParameter("bloodGroupName", bg.getBloodGroupName()).getResultList().isEmpty()) {
                if (em.find(BloodGroups.class, bg.getBloodGroupId()) != null) {
                    BloodGroups b = em.find(BloodGroups.class, bg.getBloodGroupId());
                    b.setBloodGroupName(bg.getBloodGroupName());
                    em.merge(b);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "Blood Group not found";
                }
            } else {
                res.status = false;
                res.message = "Blood Group already exists";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel deleteBloodGroup(int id) {
        ResponseModel res = new ResponseModel();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(BloodGroups.class, id) != null) {
                BloodGroups b = em.find(BloodGroups.class, id);
                if (b.getUsersCollection().isEmpty()) {
                    em.remove(b);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "This blood group is being used!";
                }
            } else {
                res.status = false;
                res.message = "Blood Group not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Collection<Degrees>> getAllDegrees() {
        ResponseModel<Collection<Degrees>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("Degrees.findAll").getResultList();
            res.status = true;
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addDegree(Degrees d) {
        ResponseModel res = new ResponseModel();
        try {
            if (d == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("Degrees.findByDegreeName").setParameter("degreeName", d.getDegreeName()).getResultList().isEmpty()) {
                em.persist(d);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Degree already exists";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updateDegree(Degrees d) {
        ResponseModel res = new ResponseModel();
        try {
            if (d == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("Degrees.findByDegreeName").setParameter("degreeName", d.getDegreeName()).getResultList().isEmpty()) {
                if (em.find(Degrees.class, d.getDegreeId()) != null) {
                    Degrees degree = em.find(Degrees.class, d.getDegreeId());
                    degree.setDegreeName(d.getDegreeName());
                    em.merge(degree);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "Degree not found";
                }
            } else {
                res.status = false;
                res.message = "Degree already exists";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel deleteDegree(int id) {
        ResponseModel res = new ResponseModel();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Degrees.class, id) != null) {
                Degrees b = em.find(Degrees.class, id);
                if (b.getDoctorDetailsCollection().isEmpty()) {
                    em.remove(b);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "This degree is being used!";
                }
            } else {
                res.status = false;
                res.message = "Degree not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

//    @Override
//    public ResponseModel<Collection<Districts>> getAllDistricts() {
//        ResponseModel<Collection<Districts>> res = new ResponseModel<>();
//        try {
//            res.data = em.createNamedQuery("Districts.findAll").getResultList();
//            res.status = true;
//        } catch (Exception ex) {
//            res.status = false;
//            res.message = ex.getMessage();
//        }
//        return res;
//    }
//
//    @Override
//    public ResponseModel addDistrict(Districts d) {
//        ResponseModel res = new ResponseModel();
//        try {
//            if (d == null) {
//                res.status = false;
//                res.message = "Input Invalid";
//                return res;
//            }
//            if (em.createNamedQuery("Districts.findByDistrictName").setParameter("districtName", d.getDistrictName()).getResultList().isEmpty()) {
//                em.persist(d);
//                res.status = true;
//            } else {
//                res.status = false;
//                res.message = "District already exists";
//            }
//        } catch (Exception ex) {
//            res.status = false;
//            res.message = ex.getMessage();
//        }
//        return res;
//    }
//
//    @Override
//    public ResponseModel updateDistrict(Districts d) {
//        ResponseModel res = new ResponseModel();
//        try {
//            if (d == null) {
//                res.status = false;
//                res.message = "Input Invalid";
//                return res;
//            }
//            if (em.createNamedQuery("Districts.findByDistrictName").setParameter("districtName", d.getDistrictName()).getResultList().isEmpty()) {
//                if (em.find(Districts.class, d.getDistrictId()) != null) {
//                    Districts district = em.find(Districts.class, d.getDistrictId());
//                    district.setDistrictName(d.getDistrictName());
//                    em.merge(district);
//                    res.status = true;
//                } else {
//                    res.status = false;
//                    res.message = "District not found";
//                }
//            } else {
//                res.status = false;
//                res.message = "District already exists";
//            }
//        } catch (Exception ex) {
//            res.status = false;
//            res.message = ex.getMessage();
//        }
//        return res;
//    }
//
//    @Override
//    public ResponseModel deleteDistrict(int id) {
//        ResponseModel res = new ResponseModel();
//        try {
//            if (id == 0) {
//                res.status = false;
//                res.message = "Input Invalid";
//                return res;
//            }
//            if (em.find(Districts.class, id) != null) {
//                Districts b = em.find(Districts.class, id);
//                if (b.getPincodesCollection().isEmpty()) {
//                    em.remove(b);
//                    res.status = true;
//                } else {
//                    res.status = false;
//                    res.message = "This district is being used";
//                }
//            } else {
//                res.status = false;
//                res.message = "Degree not found";
//            }
//        } catch (Exception ex) {
//            res.status = false;
//            res.message = ex.getMessage();
//        }
//        return res;
//    }
    @Override
    public ResponseModel<Collection<Symptoms>> getAllSymptoms() {
        ResponseModel<Collection<Symptoms>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("Symptoms.findAll").getResultList();
            res.status = true;
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addSymptom(Symptoms s) {
        ResponseModel res = new ResponseModel();
        try {
            if (s == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("Symptoms.findBySymptomName").setParameter("symptomName", s.getSymptomName()).getResultList().isEmpty()) {
                em.persist(s);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Symptom already exists";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updateSymptom(Symptoms s) {
        ResponseModel res = new ResponseModel();
        try {
            if (s == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("Symptoms.findBySymptomName").setParameter("symptomName", s.getSymptomName()).getResultList().isEmpty()) {
                if (em.find(Symptoms.class, s.getSymptomId()) != null) {
                    Symptoms symptom = em.find(Symptoms.class, s.getSymptomId());
                    symptom.setSymptomName(s.getSymptomName());
                    em.merge(symptom);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "Symptom not found";
                }
            } else {
                res.status = false;
                res.message = "Symptom already exists";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel deleteSymptom(int id) {
        ResponseModel res = new ResponseModel();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Symptoms.class, id) != null) {
                Symptoms s = em.find(Symptoms.class, id);
                if (s.getDiseasesCollection().isEmpty()) {
                    em.remove(s);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "This Symptom is being used!";
                }
            } else {
                res.status = false;
                res.message = "Symptom not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Symptoms> getSymptomById(int id) {
        ResponseModel<Symptoms> res = new ResponseModel<>();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Symptoms.class, id) != null) {
                res.data = em.find(Symptoms.class, id);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Symptom not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Collection<CommonMedications>> getAllCommonMedications() {
        ResponseModel<Collection<CommonMedications>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("CommonMedications.findAll").getResultList();
            res.status = true;
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addCommonMedication(CommonMedications cm) {
        ResponseModel res = new ResponseModel();
        try {
            if (cm == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("CommonMedications.findByMedicationName").setParameter("medicationName", cm.getMedicationName()).getResultList().isEmpty()) {
                em.persist(cm);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Common Medication already exists";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updateCommonMedication(CommonMedications cm) {
        ResponseModel res = new ResponseModel();
        try {
            if (cm == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("CommonMedications.findByMedicationName").setParameter("medicationName", cm.getMedicationName()).getResultList().isEmpty()) {
                if (em.find(CommonMedications.class, cm.getMedicationId()) != null) {
                    CommonMedications c = em.find(CommonMedications.class, cm.getMedicationId());
                    c.setMedicationName(cm.getMedicationName());
                    em.merge(c);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "Common Medication not found";
                }
            } else {
                res.status = false;
                res.message = "Common Medication already exists";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel deleteCommonMedication(int id) {
        ResponseModel res = new ResponseModel();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(CommonMedications.class, id) != null) {
                CommonMedications cm = em.find(CommonMedications.class, id);
                if (cm.getDiseasesCollection().isEmpty()) {
                    em.remove(cm);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "This Medication is being used!";
                }
            } else {
                res.status = false;
                res.message = "Common Medication not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<CommonMedications> getCommonMedicationById(int id) {
        ResponseModel<CommonMedications> res = new ResponseModel<>();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(CommonMedications.class, id) != null) {
                res.data = em.find(CommonMedications.class, id);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Common Medication not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Collection<Diseases>> getAllDiseases() {
        ResponseModel<Collection<Diseases>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("Diseases.findAll").getResultList();
            res.status = true;
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addDisease(Diseases d) {
        ResponseModel res = new ResponseModel();
        try {
            if (d == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("Diseases.findByDiseaseName").setParameter("diseaseName", d.getDiseaseName()).getResultList().isEmpty()) {
                Collection<Integer> mIds = new ArrayList<>();
                for (CommonMedications commonMedications : d.getCommonMedicationsCollection()) {
                    mIds.add(commonMedications.getMedicationId());
                }
                Collection<Integer> sIds = new ArrayList<>();
                for (Symptoms symptoms : d.getSymptomsCollection()) {
                    sIds.add(symptoms.getSymptomId());
                }
                d.setCommonMedicationsCollection(null);
                d.setSymptomsCollection(null);
                em.persist(d);
                Diseases disease = (Diseases) em.createNamedQuery("Diseases.findByDiseaseName").setParameter("diseaseName", d.getDiseaseName()).getSingleResult();
                addMedicationToDisease(disease.getDiseaseId(), mIds);
                addSymptomToDisease(disease.getDiseaseId(), sIds);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Disease already exists";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    public ResponseModel addMedicationToDisease(int dId, Collection<Integer> mIds) {
        ResponseModel res = new ResponseModel();
        try {
            if (dId == 0 && mIds.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (getDiseaseById(dId).status) {
                Diseases d = getDiseaseById(dId).data;
                Collection<CommonMedications> medications = d.getCommonMedicationsCollection();

                for (Integer mId : mIds) {
                    ResponseModel<CommonMedications> r = getCommonMedicationById(mId);
                    if (r.status) {
                        CommonMedications cm = r.data;

                        if (!medications.contains(cm)) {
                            Collection<Diseases> diseases = cm.getDiseasesCollection();
                            medications.add(cm);
                            diseases.add(d);

                            d.setCommonMedicationsCollection(medications);
                            cm.setDiseasesCollection(diseases);

                            em.merge(d);
                        }
                    } else {
                        continue;
                    }
                }
                res.status = true;
            } else {
                res.status = false;
                res.message = "Disease not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    public ResponseModel addSymptomToDisease(int dId, Collection<Integer> sIds) {
        ResponseModel res = new ResponseModel();
        try {
            if (dId == 0 && sIds.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (getDiseaseById(dId).status) {
                Diseases d = getDiseaseById(dId).data;
                Collection<Symptoms> symptoms = d.getSymptomsCollection();

                for (Integer sId : sIds) {
                    ResponseModel<Symptoms> r = getSymptomById(sId);
                    if (r.status) {
                        Symptoms s = r.data;

                        if (!symptoms.contains(s)) {
                            Collection<Diseases> diseases = s.getDiseasesCollection();
                            symptoms.add(s);
                            diseases.add(d);

                            d.setSymptomsCollection(symptoms);
                            s.setDiseasesCollection(diseases);

                            em.merge(d);
                        }
                    } else {
                        continue;
                    }
                }
                res.status = true;
            } else {
                res.status = false;
                res.message = "Symptom not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    public ResponseModel removeMedicationsToDisease(int dId, Collection<Integer> mIds) {
        ResponseModel res = new ResponseModel();
        try {
            if (dId == 0 && mIds.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (getDiseaseById(dId).status) {
                Diseases d = getDiseaseById(dId).data;
                Collection<CommonMedications> medications = d.getCommonMedicationsCollection();

                for (Integer mId : mIds) {
                    ResponseModel<CommonMedications> r = getCommonMedicationById(mId);
                    if (r.status) {
                        CommonMedications cm = r.data;

                        if (medications.contains(cm)) {
                            Collection<Diseases> diseases = cm.getDiseasesCollection();
                            medications.remove(cm);
                            diseases.remove(d);

                            d.setCommonMedicationsCollection(medications);
                            cm.setDiseasesCollection(diseases);

                            em.merge(d);
                        }
                    } else {
                        continue;
                    }
                }
                res.status = true;
            } else {
                res.status = false;
                res.message = "Disease not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    public ResponseModel removeSymptomsToDisease(int dId, Collection<Integer> sIds) {
        ResponseModel res = new ResponseModel();
        try {
            if (dId == 0 && sIds.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (getDiseaseById(dId).status) {
                Diseases d = getDiseaseById(dId).data;
                Collection<Symptoms> symptoms = d.getSymptomsCollection();

                for (Integer sId : sIds) {
                    ResponseModel<Symptoms> r = getSymptomById(sId);
                    if (r.status) {
                        Symptoms s = r.data;

                        if (symptoms.contains(s)) {
                            Collection<Diseases> diseases = s.getDiseasesCollection();
                            symptoms.remove(s);
                            diseases.remove(d);

                            d.setSymptomsCollection(symptoms);
                            s.setDiseasesCollection(diseases);

                            em.merge(d);
                        }
                    } else {
                        continue;
                    }
                }
                res.status = true;
            } else {
                res.status = false;
                res.message = "Disease not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Diseases> getDiseaseById(int id) {
        ResponseModel<Diseases> res = new ResponseModel<>();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Diseases.class, id) != null) {
                res.data = em.find(Diseases.class, id);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Disease not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updateDisease(Diseases d) {
        ResponseModel res = new ResponseModel();
        try {
            if (d == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Diseases.class, d.getDiseaseId()) != null) {
                Diseases disease = em.find(Diseases.class, d.getDiseaseId());
                disease.setDiseaseName(d.getDiseaseName());
                disease.setDiseaseType(d.getDiseaseType());
                Collection<Integer> mIds = new ArrayList<>();
                for (CommonMedications commonMedications : disease.getCommonMedicationsCollection()) {
                    mIds.add(commonMedications.getMedicationId());
                }
                removeMedicationsToDisease(d.getDiseaseId(), mIds);
                mIds.clear();
                for (CommonMedications commonMedications : d.getCommonMedicationsCollection()) {
                    mIds.add(commonMedications.getMedicationId());
                }
                addMedicationToDisease(d.getDiseaseId(), mIds);
                Collection<Integer> sIds = new ArrayList<>();
                for (Symptoms symptoms : disease.getSymptomsCollection()) {
                    sIds.add(symptoms.getSymptomId());
                }
                removeSymptomsToDisease(d.getDiseaseId(), sIds);
                sIds.clear();
                for (Symptoms symptoms : d.getSymptomsCollection()) {
                    sIds.add(symptoms.getSymptomId());
                }
                addSymptomToDisease(d.getDiseaseId(), sIds);
                em.merge(disease);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Degree not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel deleteDisease(int id) {
        ResponseModel res = new ResponseModel();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Diseases.class, id) != null) {
                Diseases b = em.find(Diseases.class, id);
                if (b.getUsersCollection().isEmpty()) {
                    Collection<Integer> mIds = new ArrayList<>();
                    for (CommonMedications commonMedications : b.getCommonMedicationsCollection()) {
                        mIds.add(commonMedications.getMedicationId());
                    }
                    removeMedicationsToDisease(id, mIds);
                    Collection<Integer> sIds = new ArrayList<>();
                    for (Symptoms symptoms : b.getSymptomsCollection()) {
                        sIds.add(symptoms.getSymptomId());
                    }
                    removeSymptomsToDisease(id, sIds);
                    em.remove(b);
                    res.status = true;
                } else {
                    res.message = "this Disease is being used";
                    res.status = false;
                }
            } else {
                res.status = false;
                res.message = "Disease not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addFieldOfStudy(FieldOfStudy data) {
        ResponseModel res = new ResponseModel();
        try {
            if (data == null) {
                res.status = false;
                res.message = "input invalid";
                return res;
            } else {
                if (em.createNamedQuery("FieldOfStudy.findByFieldName").setParameter("fieldName", data.getFieldName()).getResultList().isEmpty()) {
                    em.persist(data);
                    res.status = true;
                    res.message = "input success";
                } else {
                    res.status = false;
                    res.message = "Record already exists";
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
        res.status = res.data != null;
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
        try {
            if (deletedata == null) {
                res.status = false;
                res.message = "input invalid";
            } else {
                if (deletedata.getDoctorDetailsCollection().isEmpty()) {
                    em.remove(deletedata);
                    res.status = true;
                    res.message = "Delete success";
                } else {
                    res.status = false;
                    res.message = "This field of study is being used";
                }
            }
        } catch (Exception e) {
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
        res.status = res.data != null;
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
        try {
            if (deletedata == null) {
                res.status = false;
                res.message = "input invalid";
            } else {

                em.remove(deletedata);
                res.status = true;
                res.message = "Delete success";
            }
        } catch (Exception e) {
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
        res.status = res.data != null;
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
                pin.setDistrict(data.getDistrict());
                pin.setState(data.getState());
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
        try {
            if (deletedata == null) {
                res.status = false;
                res.message = "input invalid";
            } else {

                em.remove(deletedata);
                res.status = true;
                res.message = "Delete success";
            }
        } catch (Exception e) {
            res.message = e.getMessage();
            res.status = false;
        }
        return res;
    }

//    @Override
//    public ResponseModel addState(States data) {
//        ResponseModel res = new ResponseModel();
//        try {
//            if (data == null) {
//                res.status = false;
//                res.message = "input invalid";
//                return res;
//            } else {
//                em.persist(data);
//                res.status = true;
//                res.message = "input success";
//            }
//        } catch (Exception e) {
//            res.message = e.getMessage();
//            res.status = false;
//        }
//        return res;
//    }
//
//    @Override
//    public ResponseModel getAllStates() {
//        ResponseModel<Collection<States>> res = new ResponseModel<>();
//        try {
//            res.data = em.createNamedQuery("States.findAll").getResultList();
//            res.status = true;
//        } catch (Exception e) {
//            res.status = false;
//            res.message = e.getMessage();
//        }
//        return res;
//    }
//
//    @Override
//    public ResponseModel getState(int id) {
//        ResponseModel res = new ResponseModel();
//        res.data = em.find(States.class, id);
//        res.status = res.data != null;
//        return res;
//    }
//
//    @Override
//    public ResponseModel updateState(States data) {
//        ResponseModel res = new ResponseModel();
//        try {
//            if (data == null) {
//                res.status = false;
//                res.message = "Input Invalid";
//                return res;
//            }
//
//            if (em.find(States.class, data.getStateId()) != null) {
//                States state = em.find(States.class, data.getStateId());
//                state.setStateName(data.getStateName());
//
//                em.merge(state);
//                res.status = true;
//            } else {
//                res.status = false;
//                res.message = "User not found";
//            }
//
//        } catch (Exception e) {
//            res.status = false;
//            res.message = e.getMessage();
//        }
//        return res;
//    }
//
//    @Override
//    public ResponseModel deleteState(States data) {
//        ResponseModel res = new ResponseModel();
//        States deletedata = em.find(States.class, data.getStateId());
//        try {
//            if (deletedata == null) {
//                res.status = false;
//                res.message = "input invalid";
//            } else {
//
//                em.remove(deletedata);
//                res.status = true;
//                res.message = "Delete success";
//            }
//        } catch (Exception e) {
//            res.message = e.getMessage();
//            res.status = false;
//        }
//        return res;
//    }
    @Override
    public ResponseModel getSymptom(int id) {
        ResponseModel res = new ResponseModel();
        res.data = em.find(Symptoms.class, id);
        res.status = res.data != null;
        return res;
    }

    @Override
    public ResponseModel deleteSymptom(Symptoms data) {
        ResponseModel res = new ResponseModel();
        Symptoms deletedata = em.find(Symptoms.class, data.getSymptomId());
        try {
            if (deletedata == null) {
                res.status = false;
                res.message = "input invalid";
            } else {

                em.remove(deletedata);
                res.status = true;
                res.message = "Delete success";
            }
        } catch (Exception e) {
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

    @Override
    public Collection<BloodGroupFrequency> getBloodGroupFrequency() {
        Collection<BloodGroupFrequency> res = new ArrayList<>();
        StoredProcedureQuery qry = em.createStoredProcedureQuery("getBloodGroupFrequency");
        qry.execute();
        List<Object[]> datalist = qry.getResultList();
        for (Object[] data : datalist) {
            BloodGroupFrequency bg = new BloodGroupFrequency();
            bg.setBlood_group_name(data[0].toString());
            bg.setFrequency(Long.parseLong(data[1].toString()));
            res.add(bg);
        }
        return res;
    }

    public ResponseModel<Roles> getRoleById(int id) {
        ResponseModel<Roles> res = new ResponseModel<>();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Roles.class, id) != null) {
                res.data = em.find(Roles.class, id);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Role not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addAddress(Addresses address) {
        ResponseModel res = new ResponseModel();
        try {
            if (address == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("Addresses.findByAddress").setParameter("address", address.getAddress()).getResultList().isEmpty()) {
                em.persist(address);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Address already exists";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addChronicDiseasesToUser(int userId, Collection<Integer> dIds) {
        ResponseModel res = new ResponseModel();
        try {
            if (userId == 0 && dIds.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (getUserById(userId).status) {
                Users u = getUserById(userId).data;
                Collection<Diseases> diseases = u.getDiseasesCollection();

                for (Integer dId : dIds) {
                    ResponseModel<Diseases> r = getDiseaseById(dId);
                    if (r.status) {
                        Diseases d = r.data;

                        if (!diseases.contains(d)) {
                            Collection<Users> users = d.getUsersCollection();
                            diseases.add(d);
                            users.add(u);

                            u.setDiseasesCollection(diseases);
                            d.setUsersCollection(users);

                            em.merge(u);
                        }

                    } else {
                        continue;
                    }
                }
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
    public ResponseModel addAllergiesToUser(int userId, Collection<Integer> aIds) {
        ResponseModel res = new ResponseModel();
        try {
            if (userId == 0 && aIds.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (getUserById(userId).status) {
                Users u = getUserById(userId).data;
                Collection<Allergies> allergies = u.getAllergiesCollection();

                for (Integer aId : aIds) {
                    ResponseModel<Allergies> r = getAllergyById(aId);
                    if (r.status) {
                        Allergies a = r.data;

                        if (!allergies.contains(a)) {
                            Collection<Users> users = a.getUsersCollection();
                            allergies.add(a);
                            users.add(u);

                            u.setAllergiesCollection(allergies);
                            a.setUsersCollection(users);

                            em.merge(u);
                        }

                    } else {
                        continue;
                    }
                }
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
    public ResponseModel<Users> getUserById(int id) {
        ResponseModel<Users> res = new ResponseModel<>();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Users.class, id) != null) {
                res.data = em.find(Users.class, id);
                res.status = true;
            } else {
                res.status = false;
                res.message = "User not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Allergies> getAllergyById(int id) {
        ResponseModel<Allergies> res = new ResponseModel<>();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Allergies.class, id) != null) {
                res.data = em.find(Allergies.class, id);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Allergy not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    public String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@!#$%&";
        String password = RandomStringUtils.random(8, characters);

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%&])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches()) {
            return password;
        } else {
            return generatePassword(); // recursion
        }
    }

    private ResponseModel SendMailForSendingPasswordToUser(BigInteger aadharNo, String password) {
        ResponseModel res = new ResponseModel();
        try {
            if (aadharNo.toString().length() != 12) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (!em.createNamedQuery("Users.findByAadharCardNo").setParameter("aadharCardNo", aadharNo).getResultList().isEmpty()) {
                Users u = (Users) em.createNamedQuery("Users.findByAadharCardNo").setParameter("aadharCardNo", aadharNo).getSingleResult();
                String body = "<h3>Hello " + u.getUsername() + "!</h3><p>You have been registered as a user in EHR by Admin. </p><div><h3>Credentials</h3><div><h4>Username : " + u.getUsername() + "</h4><h4>Password : " + password + "</h4></div><p>You can log into the EHR using this credential.</p>";
                mail.sendMail(u.getEmailid(), "User Credential - EHR", body);
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

    public ResponseModel addUser(Users user) {
        ResponseModel res = new ResponseModel();
        try {
            if (user == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("Users.findByAadharCardNo").setParameter("aadharCardNo", user.getAadharCardNo()).getResultList().isEmpty()) {
                String password = generatePassword();
                String hashedPassword = PasswordHash.generate(password.toCharArray());
                user.setPassword(hashedPassword);
                Collection<Integer> dids = new ArrayList<>();
                Collection<Integer> aids = new ArrayList<>();
                if (!user.getDiseasesCollection().isEmpty()) {
                    for (Diseases diseases : user.getDiseasesCollection()) {
                        dids.add(diseases.getDiseaseId());
                    }
                }
                if (!user.getAllergiesCollection().isEmpty()) {
                    for (Allergies allergies : user.getAllergiesCollection()) {
                        aids.add(allergies.getAllergyId());
                    }
                }
                user.setDiseasesCollection(null);
                user.setAllergiesCollection(null);
                if (em.find(BloodGroups.class, user.getBloodGroupId().getBloodGroupId()) != null) {
                    user.setBloodGroupId(em.find(BloodGroups.class, user.getBloodGroupId().getBloodGroupId()));
                } else {
                    user.setBloodGroupId(null);
                }
                if (em.find(Roles.class, user.getRoleId().getRoleid()) != null) {
                    user.setRoleId(em.find(Roles.class, user.getRoleId().getRoleid()));
                } else {
                    user.setRoleId(null);
                }
                ResponseModel addr = addAddress(user.getAddressId());
                if (addr.status) {
                    Addresses a = (Addresses) em.createNamedQuery("Addresses.findByAddress").setParameter("address", user.getAddressId().getAddress()).getSingleResult();
                    user.setAddressId(a);
                }
                em.persist(user);
                SendMailForSendingPasswordToUser(user.getAadharCardNo(), password);
                Users u = (Users) em.createNamedQuery("Users.findByAadharCardNo").setParameter("aadharCardNo", user.getAadharCardNo()).getSingleResult();
                if (!dids.isEmpty()) {
                    addChronicDiseasesToUser(u.getUserId(), dids);
                }
                if (!aids.isEmpty()) {
                    addAllergiesToUser(u.getUserId(), aids);
                }
                res.status = true;

            } else {
                res.status = false;
                res.message = "User already exists";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public long getAllUsersFrequency(int userid) {
        StoredProcedureQuery qry = em.createNamedStoredProcedureQuery("countUsers");
        qry.setParameter("userType", userid);
        qry.execute();
        List<Object[]> result = qry.getResultList();
        Long count = 0L;
        for (Object[] data : result) {
            count = Long.valueOf(data[0].toString());
        }
        return count;
    }

    @Override
    public Collection<DateWiseCaseFrequency> getCasesFrequency(String disease, LocalDate startDate, String state) {
        Collection<DateWiseCaseFrequency> res = new ArrayList<>();

        StoredProcedureQuery qry = em.createNamedStoredProcedureQuery("frequencyByDiseaseDateState");
        qry.setParameter("diseaseval", disease);

        qry.setParameter("dateval", startDate);

        qry.setParameter("stateval", state);
        qry.execute();
        List<Object[]> datalist = qry.getResultList();
        for (Object[] data : datalist) {
            DateWiseCaseFrequency cf = new DateWiseCaseFrequency();
            cf.setFrequencyDate(LocalDate.parse(data[0].toString(), DateTimeFormatter.ISO_DATE));
            cf.setFrequency(Long.valueOf(data[1].toString()));
            res.add(cf);
        }
        return res;
    }

    public ResponseModel<BloodGroups> getBloodGroupById(int id) {
        ResponseModel<BloodGroups> res = new ResponseModel<>();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(BloodGroups.class, id) != null) {
                res.data = em.find(BloodGroups.class, id);
                res.status = true;
            } else {
                res.status = false;
                res.message = "BloodGroup not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public Collection<DiseaseToFrequency> getTopCases() {
        Collection<DiseaseToFrequency> res = new ArrayList<>();
        StoredProcedureQuery qry = em.createStoredProcedureQuery("topTenDiseasesByCases");
        qry.execute();
        List<Object[]> datalist = qry.getResultList();
        for (Object[] data : datalist) {
            DiseaseToFrequency cf = new DiseaseToFrequency();
            cf.setDiseaseName(data[0].toString());
            cf.setFrequency(Long.valueOf(data[1].toString()));
            res.add(cf);

        }
        return res;
    }

    @Override
    public long getTotalAccess() {
        Object frequency = em.createNativeQuery("select count(*) as frequency from ehrsystem.patient_access_mapper").getSingleResult();
        return Long.parseLong(frequency.toString());
    }

    @Override
    public long getTotalAcuteCases() {
        Object frequency = em.createNativeQuery("SELECT COUNT(*) FROM patient_doctor_mapper where disease_id IN(SELECT disease_id FROM diseases where disease_type = 1)").getSingleResult();
        return Long.parseLong(frequency.toString());
    }

    @Override
    public long getTotalChronicCases() {
         Object frequency = em.createNativeQuery("SELECT COUNT(*) FROM patient_doctor_mapper where disease_id IN(SELECT disease_id FROM diseases where disease_type = 0)").getSingleResult();
        return Long.parseLong(frequency.toString());
    }

    @Override
    public ResponseModel<Collection<Diseases>> getAllChronicDiseases() {
        ResponseModel<Collection<Diseases>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("Diseases.findByDiseaseType").setParameter("diseaseType", true).getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }
}
