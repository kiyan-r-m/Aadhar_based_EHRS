/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Beans;

import Entities.ResponseModel;
import javax.ejb.Local;

/**
 *
 * @author krdmo
 */
@Local
interface EmailClientLocal {
    ResponseModel sendMail(String recepient, String subject, String body);
}
