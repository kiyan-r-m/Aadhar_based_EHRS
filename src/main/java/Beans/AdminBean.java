/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
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
            if (em.find(BloodGroups.class, bg.getBloodGroupId()) != null) {
                BloodGroups b = em.find(BloodGroups.class, bg.getBloodGroupId());
                b.setBloodGroupName(bg.getBloodGroupName());
                em.merge(b);
                res.status = true;
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
                em.remove(b);
                res.status = true;
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
            if (em.find(Degrees.class, d.getDegreeId()) != null) {
                Degrees degree = em.find(Degrees.class, d.getDegreeId());
                degree.setDegreeName(d.getDegreeName());
                em.merge(degree);
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
                em.remove(b);
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
            if (em.find(Districts.class, d.getDistrictId()) != null) {
                Districts district = em.find(Districts.class, d.getDistrictId());
                district.setDistrictName(d.getDistrictName());
                em.merge(district);
                res.status = true;
            } else {
                res.status = false;
                res.message = "District not found";
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
                em.remove(b);
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
            if (em.find(Symptoms.class, s.getSymptomId()) != null) {
                Symptoms symptom = em.find(Symptoms.class, s.getSymptomId());
                symptom.setSymptomName(s.getSymptomName());
                em.merge(symptom);
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
                em.remove(s);
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
            if (em.find(CommonMedications.class, cm.getMedicationId()) != null) {
                CommonMedications c = em.find(CommonMedications.class, cm.getMedicationId());
                c.setMedicationName(cm.getMedicationName());
                em.merge(c);
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
                em.remove(cm);
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
                if(getCommonMedicationById(d.getCommonMedicationId().getMedicationId()).status == true) {
                    int cid = d.getCommonMedicationId().getMedicationId();
                    d.setCommonMedicationId(getCommonMedicationById(cid).data);
                }
                if(getSymptomById(d.getSymptomId().getSymptomId()).status == true) {
                    int sid = d.getSymptomId().getSymptomId();
                    d.setSymptomId(getSymptomById(sid).data);
                }
                em.persist(d);
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
                if(getCommonMedicationById(d.getCommonMedicationId().getMedicationId()).status == true) {
                    disease.setCommonMedicationId(getCommonMedicationById(d.getCommonMedicationId().getMedicationId()).data);
                }
                if(getSymptomById(d.getSymptomId().getSymptomId()).status == true) {
                    disease.setSymptomId(getSymptomById(d.getSymptomId().getSymptomId()).data);
                }
                disease.setDiseaseType(d.getDiseaseType());
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
                em.remove(b);
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
}
