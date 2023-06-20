/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entities.*;
import io.jsonwebtoken.lang.Collections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@Stateless
public class userBean implements userBeanLocal {

    @PersistenceContext(unitName = "my_persistence")
    EntityManager em;

    @Inject
    private Pbkdf2PasswordHash PasswordHash;

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
                if (user.getPassword() != null) {
                    String hashedPassword = PasswordHash.generate(user.getPassword().toCharArray());
                    user.setPassword(hashedPassword);
                }
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
                u.setContactNo(user.getContactNo());
                u.setGender(user.getGender());
                u.setDob(user.getDob());
                if (em.find(BloodGroups.class, user.getBloodGroupId().getBloodGroupId()) != null) {
                    u.setBloodGroupId(em.find(BloodGroups.class, user.getBloodGroupId().getBloodGroupId()));
                }
                if (em.find(Roles.class, user.getRoleId().getRoleid()) != null) {
                    u.setRoleId(em.find(Roles.class, user.getRoleId().getRoleid()));
                }
                ResponseModel addr = updateAddresses(user.getAddressId());
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
    public ResponseModel removeUser(int id) {
        ResponseModel res = new ResponseModel();
        try {
            if (id == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.find(Users.class, id) != null) {
                Users u = em.find(Users.class, id);
                Collection<Integer> dids = new ArrayList<>();
                for (Diseases diseases : u.getDiseasesCollection()) {
                    dids.add(diseases.getDiseaseId());
                }
                removeChronicDiseasesToUser(u.getUserId(), dids);
                Collection<Integer> aids = new ArrayList<>();
                for (Allergies allergies : u.getAllergiesCollection()) {
                    aids.add(allergies.getAllergyId());
                }
                removeAllergiesToUser(u.getUserId(), aids);
                em.remove(u);
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
                if (PasswordHash.verify(oldPassword.toCharArray(), u.getPassword())) {
                    u.setPassword(PasswordHash.generate(newPassword.toCharArray()));
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
    public ResponseModel SendMailForForgetPassword(String emailId, HttpServletRequest request) {
        ResponseModel res = new ResponseModel();
        try {
            if (emailId.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (!em.createNamedQuery("Users.findByEmailId").setParameter("emailId", emailId).getResultList().isEmpty()) {
                Users u = (Users) em.createNamedQuery("Users.findByEmailId").setParameter("emailId", emailId).getSingleResult();
                long time = System.currentTimeMillis() + 3600000;
//                HttpSession session = request.getSession(true);
                ServletContext context = request.getServletContext();
                context.setAttribute(u.getUserId().toString(), String.valueOf(time));
//                session.setAttribute(u.getUserId().toString(), String.valueOf(time));
                String link = "http://localhost:8082/Aadhar_based_EHRS/resetPassword.jsf?user=" + u.getUserId() + "," + time;
                String body = "<h3>Hello " + u.getUsername()
                        + "!</h3><p><a href='" + link
                        + "'>Click here to reset Password</a></p><br/><p>This link is available for only one hour. So please reset password before that.</p><br/><p>If you did not request a password reset, you can ignore this message</p>";
                mail.sendMail(u.getEmailid(), "Forgot Password - EHR", body);
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
    public ResponseModel ForgetPassword(HttpServletRequest request, String newPassword) {
        ResponseModel res = new ResponseModel();
        try {
            if (newPassword == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
//            int UserId = 0;
//            if (!queryReq.isEmpty()) {
//                String[] values = queryReq.split(",");
//                if (!values[0].isEmpty()) {
//                    UserId = Integer.parseInt(values[0]);
//                }
//            }
            HttpSession session = request.getSession(true);
            Enumeration<String> e = session.getAttributeNames();
            int UserId = 0;
            if (Collections.contains(e, "userId")) {
                UserId = Integer.parseInt(session.getAttribute("userId").toString());
            }
            if (UserId == 0) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            } else {
                ServletContext context = request.getServletContext();
                if (em.find(Users.class, UserId) != null) {
                    Users u = em.find(Users.class, UserId);
                    u.setPassword(PasswordHash.generate(newPassword.toCharArray()));
                    em.merge(u);
                    session.removeAttribute("userId");
                    context.removeAttribute(String.valueOf(UserId));
                    res.status = true;
                } else {
                    res.status = false;
                    res.message = "User not found";
                }
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

//    private Cookie getCookie(String CookieName, HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//
//        if (cookies != null) {
//            for (Cookie c : cookies) {
//                if (c.getName().equals(CookieName)) {
//                    return c;
//                }
//            }
//        }
//        return null;
//    }
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

    @Override
    public ResponseModel getUserByAadharPassword(String aadhar, String password) {
        ResponseModel<Users> res = new ResponseModel<>();
        try {
            if (aadhar.isEmpty() || password.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            res.data = (Users) em.createNamedQuery("Users.findByAadharPassword")
                    .setParameter("aadharCardNo", aadhar)
                    .setParameter("password", password).getSingleResult();
            if (res.data != null) {
                res.status = true;
                return res;
            } else {
                res.status = false;
                res.message = "User not found";
                return res;
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
            return res;
        }

    }

    @Override
    public ResponseModel<Users> getUserByUsernamePassword(String username, String password) {
        ResponseModel<Users> res = new ResponseModel<>();
        try {
            if (username.isEmpty() || password.isEmpty()) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            Users user = (Users) em.createNamedQuery("Users.findByUsername")
                    .setParameter("username", username).getSingleResult();
            if (user != null) {
                if (PasswordHash.verify(password.toCharArray(), user.getPassword())) {
                    res.data = user;
                    res.status = true;
                    return res;
                } else {
                    res.status = false;
                    res.message = "Something went wrong";
                    return res;
                }
            } else {
                res.status = false;
                res.message = "User not found";
                return res;
            }
        } catch (Exception ex) {
            res.status = false;
            res.message = ex.getMessage();
            return res;
        }
    }

    @Override
    public ResponseModel<Collection<Roles>> getAllRoles() {
        ResponseModel<Collection<Roles>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("Roles.findAll").getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<ArrayList<DoctorNotes>> getNotesById(int id) {
        ResponseModel<ArrayList<DoctorNotes>> res = new ResponseModel<>();
        try{
            PatientDoctorMapper pd = em.find(PatientDoctorMapper.class, id);
            ArrayList<DoctorNotes> arr = new ArrayList<>(pd.getDoctorNotesCollection());
            res.data = arr;
            res.status = true;
        }
        catch(Exception e)
        {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<PatientDoctorMapper> getPatientDoctorMapperById(int id) {
        ResponseModel<PatientDoctorMapper> res = new ResponseModel<>();
        try{
            res.data = em.find(PatientDoctorMapper.class, id);
            res.status = true;
        }
        catch(Exception e)
        {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }
}
