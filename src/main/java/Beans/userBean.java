/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import com.mycompany.aadhar_based_ehrs.Users;
import java.util.Collection;
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

    
}
