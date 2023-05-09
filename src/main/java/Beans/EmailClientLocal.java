/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import Entities.ResponseModel;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface EmailClientLocal {
    ResponseModel sendMail(String recepient, String subject, String body);
}
