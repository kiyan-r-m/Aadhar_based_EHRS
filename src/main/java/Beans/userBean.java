/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entities.Addresses;
import Entities.Allergies;
import Entities.Appointments;
import Entities.PatientAccessMapper;
import Entities.PatientDoctorMapper;
import Entities.ResponseModel;
import Entities.Users;
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

    @PersistenceContext(unitName = "ehrJPU")
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
            if (em.createNamedQuery("Users.findByAadharno").setParameter("aadharno", user.getAadharCardNo()).getResultList() == null) {
                em.persist(user);
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
                u.setBloodGroupsCollection(user.getBloodGroupsCollection());
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
            res.data = em.createNamedQuery("Appointments.findByPatientid").setParameter("patientid", userId).getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Addresses> getAddressByUserId(int userId) {
        ResponseModel<Addresses> res = new ResponseModel<>();
        try {
            res.data = (Addresses) em.createNamedQuery("Addresses.findByUserid").setParameter("userid", userId).getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel addAddressForUserId(Addresses address) {
        ResponseModel res = new ResponseModel();
        try {
            if (address == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }
            if (em.createNamedQuery("Addresses.findByUserid").setParameter("userid", address.getUserId()).getResultList() == null) {
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
    public ResponseModel updateAddressesForUserId(Addresses address) {
        ResponseModel res = new ResponseModel();
        try {
            if (address == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.createNamedQuery("Addresses.findByUserid").setParameter("userid", address.getUserId()).getResultList() != null) {
                Addresses a = (Addresses) em.createNamedQuery("Addresses.findByUserid").setParameter("userid", address.getUserId()).getResultList();
                a.setAddress(address.getAddress());
                a.setPincode(address.getPincode());
                em.merge(a);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Address for this user not found";
            }

        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel<Collection<PatientAccessMapper>> getAccessMapperByUserId(int userId) {
        ResponseModel<Collection<PatientAccessMapper>> res = new ResponseModel<>();
        try {
            res.data = em.createNamedQuery("PatientAccessMapper.findByPatientid").setParameter("patientid", userId).getResultList();
            res.status = true;
        } catch (Exception e) {
            res.status = false;
            res.message = e.getMessage();
        }
        return res;
    }

    @Override
    public ResponseModel updateAccessMapperForUserId(PatientAccessMapper accessMapper) {
        ResponseModel res = new ResponseModel();
        try {
            if (accessMapper == null) {
                res.status = false;
                res.message = "Input Invalid";
                return res;
            }

            if (em.find(PatientAccessMapper.class, accessMapper.getPatientAccessMapperPK()) != null) {
                PatientAccessMapper a = em.find(PatientAccessMapper.class, accessMapper.getPatientAccessMapperPK());
                a.setDoctorDetails(accessMapper.getDoctorDetails());
                a.setIsActive(accessMapper.getIsActive());
                em.merge(a);
                res.status = true;
            } else {
                res.status = false;
                res.message = "Access not found";
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
            res.data = em.createNamedQuery("PatientDoctorMapper.findByPatientid").setParameter("patientid", userId).getResultList();
            res.status = true;
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

            if (em.createNamedQuery("Users.findByEmailid").setParameter("emailid", emailId).getResultList() != null) {
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
}
