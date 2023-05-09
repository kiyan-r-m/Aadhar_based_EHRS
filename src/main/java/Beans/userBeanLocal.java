/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
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
import java.util.Collection;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@Local
public interface userBeanLocal {
    ResponseModel<Collection<Users>> getAllUsers();
    ResponseModel addUser(Users user);
    ResponseModel updateUser(Users user);
    ResponseModel<Collection<Allergies>> getAllAllergies();
    ResponseModel<Collection<Appointments>> getAppointmentsByUserId(int userId);
    ResponseModel<Addresses> getAddressByUserId(int userId);
    ResponseModel addAddress(Addresses address);
    ResponseModel updateAddresses(Addresses address);
    ResponseModel<Collection<DoctorDetails>> getAccessMapperByUserId(int userId);
    ResponseModel addAccessMapperToUser(int userId, Collection<Integer> dIds);
    ResponseModel removeAccessMapperToUser(int userId, Collection<Integer> dIds);
    ResponseModel updateAccessMapperToUser(int userId, Collection<Integer> dIds);
    ResponseModel<Collection<PatientDoctorMapper>> getPatientDoctorMapperByUserId(int userId);
    ResponseModel ChangePassword(int userId, String oldPassword, String newPassword);
//    ResponseModel SendMailForForgetPassword(String emailId, HttpServletResponse response);
    ResponseModel ForgetPassword(HttpServletRequest request, HttpServletResponse response, int userId, String OTP, String newPassword);
    ResponseModel<Collection<BloodGroups>> getAllBloodGroups();
    ResponseModel<BloodGroups> getBloodGroups(int id);
    ResponseModel<Users> getUserById(int id);
    ResponseModel addChronicDiseasesToUser(int userId, Collection<Integer> dIds);
    ResponseModel removeChronicDiseasesToUser(int userId, Collection<Integer> dIds);
    ResponseModel<Diseases> getDiseaseById(int id);
    ResponseModel addAllergiesToUser(int userId, Collection<Integer> aIds);
    ResponseModel removeAllergiesToUser(int userId, Collection<Integer> aIds);
    ResponseModel<Allergies> getAllergyById(int id);
    ResponseModel<DoctorDetails> getDoctorDetailById(int id);
    ResponseModel<Collection<Addresses>> getAllAddresses();
    ResponseModel getUserByAadharPassword(String aadhar, String password);
    ResponseModel getUserByUsernamePassword(String username, String password);

    ResponseModel removeUser(int id);

    ResponseModel<Collection<Roles>> getAllRoles();
}
