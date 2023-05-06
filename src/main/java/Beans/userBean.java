/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entities.Addresses;
import Entities.Allergies;
import Entities.Appointments;
import Entities.BloodGroups;
import Entities.Diseases;
import Entities.DoctorDetails;
import Entities.PatientDoctorMapper;
import Entities.ResponseModel;
import Entities.Roles;
import Entities.Users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@Stateless
public class userBean implements userBeanLocal {

    @PersistenceContext(unitName = "my_persistence")
    EntityManager em;

    @EJB
    EmailClientLocal mail;

    @Override
    public ResponseModel<Collection<Users>> getAllUsers() {
        ResponseModel<Collection<Users>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("Users.findAll").getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addUser(Users user) {
        ResponseModel res = new ResponseModel();
        try {
            if (user == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("Users.findByAadharCardNo").setParameter("aadharCardNo", user.getAadharCardNo()).getResultList().isEmpty()) {
                Collection<Integer> dids = new ArrayList<>();
                for (Diseases diseases : user.getDiseasesCollection()) {
                    dids.add(diseases.getDiseaseId());
                }
                Collection<Integer> aids = new ArrayList<>();
                for (Allergies allergies : user.getAllergiesCollection()) {
                    aids.add(allergies.getAllergyId());
                }
                user.setDiseasesCollection(null);
                user.setAllergiesCollection(null);
                em.persist(user);
                if(em.find(BloodGroups.class, user.getBloodGroupId().getBloodGroupId()) != null) {
                    user.setBloodGroupId(em.find(BloodGroups.class, user.getBloodGroupId().getBloodGroupId()));
                } else {
                    user.getBloodGroupId(null);
                }
                if (em.find(Roles.class, user.getRoleId().getRoleid()) != null) {
                    user.setRoleId(em.find(Roles.class, user.getRoleId().getRoleid()));
                } else {
                    user.setRoleId(null);
                }
                if (em.find(Addresses.class, user.getAddressId().getAddressId()) != null) {
                    user.setAddressId(em.find(Addresses.class, user.getAddressId().getAddressId()));
                } else {
                    user.setAddressId(null);
                }
                Users u = (Users) em.createNamedQuery("Users.findByAadharCardNo").setParameter("aadharCardNo", user.getAadharCardNo()).getSingleResult();
                addChronicDiseasesToUser(u.getUserId(), dids);
                addAllergiesToUser(u.getUserId(), aids);
                res.status = true;
            } else {
                Collection<Users> users = em.createNamedQuery("Users.findByAadharCardNo").setParameter("aadharCardNo", user.getAadharCardNo()).getResultList();
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
    public ResponseModel updateUser(Users user) {
        ResponseModel res = new ResponseModel();
        try {
            if (user == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(Users.class, user.getUserId()) != null) {
                Users u = em.find(Users.class, user.getUserId());
                u.setUsername(user.getUsername());
                u.setEmailid(user.getEmailid());
                u.setAadharCardNo(user.getAadharCardNo());
                u.setRoleId(user.getRoleId());
                u.setContactNo(user.getContactNo());
                u.setGender(user.getGender());
                u.setDob(user.getDob());
                if(em.find(BloodGroups.class, user.getBloodGroupId().getBloodGroupId()) != null) {
                    u.setBloodGroupId(em.find(BloodGroups.class, user.getBloodGroupId().getBloodGroupId()));
                }
                if (em.find(Roles.class, user.getRoleId().getRoleid()) != null) {
                    u.setRoleId(em.find(Roles.class, user.getRoleId().getRoleid()));
                }
                if (em.find(Addresses.class, user.getAddressId().getAddressId()) != null) {
                    u.setAddressId(em.find(Addresses.class, user.getAddressId().getAddressId()));
                }
                Collection<Integer> dids = new ArrayList<>();
                for (Diseases diseases : u.getDiseasesCollection()) {
                    dids.add(diseases.getDiseaseId());
                }
                removeChronicDiseasesToUser(u.getUserId(), dids);
                for (Diseases diseases : user.getDiseasesCollection()) {
                    dids.add(diseases.getDiseaseId());
                }
                addChronicDiseasesToUser(u.getUserId(), dids);
                Collection<Integer> aids = new ArrayList<>();
                for (Allergies allergies : u.getAllergiesCollection()) {
                    aids.add(allergies.getAllergyId());
                }
                removeAllergiesToUser(u.getUserId(), aids);
                for (Allergies allergies : user.getAllergiesCollection()) {
                    aids.add(allergies.getAllergyId());
                }
                addAllergiesToUser(u.getUserId(), aids);
                em.merge(u);
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
    public ResponseModel removeChronicDiseasesToUser(int userId, Collection<Integer> dIds) {
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

                        if (diseases.contains(d)) {
                            Collection<Users> users = d.getUsersCollection();
                            diseases.remove(d);
                            users.remove(u);

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
    public ResponseModel removeAllergiesToUser(int userId, Collection<Integer> aIds) {
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

                        if (allergies.contains(a)) {
                            Collection<Users> users = a.getUsersCollection();
                            allergies.remove(a);
                            users.remove(u);

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
    public ResponseModel<Collection<Allergies>> getAllAllergies() {
        ResponseModel<Collection<Allergies>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("Allergies.findAll").getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Collection<Appointments>> getAppointmentsByUserId(int userId) {
        ResponseModel<Collection<Appointments>> res = new ResponseModel<>();
        try {
            if (userId == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            ResponseModel<Users> r = getUserById(userId);
            if (r.status) {
                res.data = r.data.getAppointmentsCollection();
                res.status = true;
            } else {
                res.status = false;
                res.message = r.message;
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Addresses> getAddressByUserId(int userId) {
        ResponseModel<Addresses> res = new ResponseModel<>();
        try {
            if (userId == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            ResponseModel<Users> r = getUserById(userId);
            if (r.status) {
                res.data = r.data.getAddressId();
                res.status = true;
            } else {
                res.status = false;
                res.message = r.getMessage();
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }
    
    @Override
    public ResponseModel<Collection<Addresses>> getAllAddresses() {
        ResponseModel<Collection<Addresses>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("Addresses.findAll").getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
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
    public ResponseModel updateAddresses(Addresses address) {
        ResponseModel res = new ResponseModel();
        try {
            if (address == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(Addresses.class, address.getAddressId()) != null) {
                Addresses a = em.find(Addresses.class, address.getAddressId());
                a.setAddress(address.getAddress());
                a.setPincode(address.getPincode());
                em.merge(a);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Address not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Collection<DoctorDetails>> getAccessMapperByUserId(int userId) {
        ResponseModel<Collection<DoctorDetails>> res = new ResponseModel<>();
        try {
            if (userId == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            
            ResponseModel<Users> r = getUserById(userId);
            if (r.status) {
                res.data = r.data.getDoctorDetailsCollection();
                res.status = true;
            } else {
                res.status = false;
                res.message = r.getMessage();
            }
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addAccessMapperToUser(int userId, Collection<Integer> dIds) {
        ResponseModel res = new ResponseModel();
        try {
            if (userId == 0 && dIds.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (getUserById(userId).status) {
                Users u = getUserById(userId).data;
                Collection<DoctorDetails> accesses = u.getDoctorDetailsCollection();

                for (Integer dId : dIds) {
                    ResponseModel<DoctorDetails> r = getDoctorDetailById(dId);
                    if (r.status) {
                        DoctorDetails d = r.data;

                        if (!accesses.contains(d)) {
                            Collection<Users> users = d.getUsersCollection();
                            accesses.add(d);
                            users.add(u);

                            u.setDoctorDetailsCollection(accesses);
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
    public ResponseModel removeAccessMapperToUser(int userId, Collection<Integer> dIds) {
        ResponseModel res = new ResponseModel();
        try {
            if (userId == 0 && dIds.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (getUserById(userId).status) {
                Users u = getUserById(userId).data;
                Collection<DoctorDetails> accesses = u.getDoctorDetailsCollection();

                for (Integer dId : dIds) {
                    ResponseModel<DoctorDetails> r = getDoctorDetailById(dId);
                    if (r.status) {
                        DoctorDetails d = r.data;

                        if (accesses.contains(d)) {
                            Collection<Users> users = d.getUsersCollection();
                            accesses.remove(d);
                            users.remove(u);

                            u.setDoctorDetailsCollection(accesses);
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
    public ResponseModel updateAccessMapperToUser(int userId, Collection<Integer> dIds) {
        ResponseModel res = new ResponseModel();
        try {
            if (userId == 0 && dIds.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (getUserById(userId).status) {
                Users u = getUserById(userId).data;
                Collection<DoctorDetails> accesses = u.getDoctorDetailsCollection();
                
                Collection<Integer> ids = new ArrayList<>();
                for (DoctorDetails access : accesses) {
                    ids.add(access.getDoctorId());
                }
                removeAccessMapperToUser(userId, ids);
                addAccessMapperToUser(userId, dIds);
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
    public ResponseModel<Collection<PatientDoctorMapper>> getPatientDoctorMapperByUserId(int userId) {
        ResponseModel<Collection<PatientDoctorMapper>> res = new ResponseModel<>();
        try {
            if (userId == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            
            ResponseModel<Users> r = getUserById(userId);
            if (r.status) {
                res.data = r.data.getPatientDoctorMapperCollection();
                res.status = true;
            } else {
                res.status = false;
                res.message = r.getMessage();
            }
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel ChangePassword(int userId, String oldPassword, String newPassword) {
        ResponseModel res = new ResponseModel();
        try {
            if ((Integer.valueOf(userId) == null || userId == 0) || (oldPassword == null || oldPassword.equals("")) || (newPassword == null || newPassword.equals(""))) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(Users.class, userId) != null) {
                Users u = em.find(Users.class, userId);
                if (u.getPassword().equals(oldPassword)) {
                    u.setPassword(newPassword);
                    em.merge(u);
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "Wrong Password";
                    return res;
                }
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
    public ResponseModel SendMailForForgetPassword(String emailId, HttpServletResponse response) {
        ResponseModel res = new ResponseModel();
        try {
            if (emailId.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (!em.createNamedQuery("Users.findByEmailid").setParameter("emailid", emailId).getResultList().isEmpty()) {
                Collection<Users> u = em.createNamedQuery("Users.findByEmailid").setParameter("emailid", emailId).getResultList();
                String otp = UUID.randomUUID().toString();
                Cookie cookie = new Cookie("forgetPasswordOTP", otp);
                response.addCookie(cookie);
                if (u.size() == 1) {
                    Users u1 = u.iterator().next();
                    String body = "<h3>Hello " + u1.getUsername() + "!</h3><p>Your OTP for forget password is " + otp + "</p><br/><p>Enter this OTP for creating the new password</p>";
                    mail.sendMail(u1.getEmailid(), "Forget Password OTP", body);
                    res.status = true;
                }
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
    public ResponseModel ForgetPassword(HttpServletRequest request, HttpServletResponse response, int userId, String OTP, String newPassword) {
        ResponseModel res = new ResponseModel();
        try {
            if (userId == 0 || newPassword == null || OTP == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Users.class, userId) != null) {
                Cookie cookie = getCookie("forgetPasswordOTP", request);
                if (cookie != null) {
                    if (cookie.getValue().toString().equals(OTP)) {
                        Users u = em.find(Users.class, userId);
                        u.setPassword(newPassword);
                        em.merge(u);
                        res.status = true;
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    } else {
                        res.status = false;
                        res.message = "Incorrect OTP";
                        return res;
                    }
                } else {
                    res.status = false;
                    res.message = "Something went wrong";
                    return res;
                }
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

    private Cookie getCookie(String CookieName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(CookieName)) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public ResponseModel<Collection<BloodGroups>> getAllBloodGroups() {
        ResponseModel<Collection<BloodGroups>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("BloodGroups.findAll").getResultList();
            res.status = true;
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

    @Override
    public ResponseModel<DoctorDetails> getDoctorDetailById(int id) {
        ResponseModel<DoctorDetails> res = new ResponseModel<>();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(DoctorDetails.class, id) != null) {
                res.data = em.find(DoctorDetails.class, id);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Doctor not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<BloodGroups> getBloodGroups(int id) {
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
                res.message = "Bloodgroup not found";
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
        }
        return res;
    }
}
