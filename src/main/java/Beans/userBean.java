/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import com.mycompany.aadhar_based_ehrs.Users;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author admin
 */
@Stateless
public class userBean implements userBeanLocal {

    @PersistenceContext(unitName = "ehrJPU")
    EntityManager em;
    
    @Override
    public Collection<Users> getAllUsers() {
        return em.createNamedQuery("Users.findAll").getResultList();
    }

    @Override
    public void addUser(String username, String email, String password, BigInteger aadharNo, int roleid, BigInteger contactNo, String gender, Date dob, int bloodGroupId)
    {
        Users user = new Users();
        user.setUsername(username);
        user.setEmailid(email);
        user.setPassword(password);
        user.setAadharno(aadharNo);
        user.setRoleid(roleid);
        user.setContactno(contactNo);
        user.setGender(gender);
        user.setDob(dob);
        user.setBloodgroupid(bloodGroupId);
        em.persist(user);
    }
    
    
}
