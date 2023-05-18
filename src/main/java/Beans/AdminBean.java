/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entities.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author admin
 */
@Stateless
public class AdminBean implements AdminBeanLocal {

    @PersistenceContext(unitName = "my_persistence")
    private EntityManager em;

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

    @Override
    public ResponseModel<Collection<Districts>> getAllDistricts() {
        ResponseModel<Collection<Districts>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("Districts.findAll").getResultList();
            res.status = true;
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addDistrict(Districts d) {
        ResponseModel res = new ResponseModel();
        try {
            if (d == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("Districts.findByDistrictName").setParameter("districtName", d.getDistrictName()).getResultList().isEmpty()) {
                em.persist(d);
                res.status = true;
            } else {
                res.status = false;
                res.message = "District already exists";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updateDistrict(Districts d) {
        ResponseModel res = new ResponseModel();
        try {
            if (d == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("Districts.findByDistrictName").setParameter("districtName", d.getDistrictName()).getResultList().isEmpty()) {
                if (em.find(Districts.class, d.getDistrictId()) != null) {
                    Districts district = em.find(Districts.class, d.getDistrictId());
                    district.setDistrictName(d.getDistrictName());
                    em.merge(district);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "District not found";
                }
            } else {
                res.status = false;
                res.message = "District already exists";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel deleteDistrict(int id) {
        ResponseModel res = new ResponseModel();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Districts.class, id) != null) {
                Districts b = em.find(Districts.class, id);
                if (b.getPincodesCollection().isEmpty()) {
                    em.remove(b);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "This district is being used";
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
        FieldOfStudy addData = em.find(FieldOfStudy.class, data.getFieldId());
        try {
            if (data == null) {
                res.status = false;
                res.message = "input invalid";
                return res;
            } else {
                if (addData == null) {
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
        res.status = res.data != null;
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
}
