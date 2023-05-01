/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
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
    ResponseModel addAddressForUserId(Addresses address);
    ResponseModel updateAddressesForUserId(Addresses address);
    ResponseModel<Collection<PatientAccessMapper>> getAccessMapperByUserId(int userId);
    ResponseModel updateAccessMapperForUserId(PatientAccessMapper accessMapper);
    ResponseModel<Collection<PatientDoctorMapper>> getPatientDoctorMapperByUserId(int userId);
    ResponseModel ChangePassword(int userId, String oldPassword, String newPassword);
    ResponseModel SendMailForForgetPassword(String emailId, HttpServletResponse response);
    ResponseModel ForgetPassword(HttpServletRequest request, HttpServletResponse response, int userId, String OTP, String newPassword);
}
