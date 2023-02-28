/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import com.mycompany.aadhar_based_ehrs.Users;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface userBeanLocal {
    
    Collection<Users> getAllUsers();
    void addUser(String username, String email, String password, BigInteger aadharNo, int roleid, BigInteger contactNo, String gender, Date dob, int bloodGroupId);
    
}
