/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entities.ResponseModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author admin
 */
@Stateless
public class EmailClient implements EmailClientLocal {

    Properties p = System.getProperties();

    public EmailClient() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("EmailConfigProperties.properties");
            p.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ResponseModel sendMail(String recepient, String subject, String body) {
        ResponseModel res = new ResponseModel();
        try {
            Properties prop = setMailProperties();

            Session mailSession = setMailSession(prop);

            // create message using session
            Message mailMessage = new MimeMessage(mailSession);
            SetMailMime(mailMessage, recepient, subject, body);
            
            // sending message
            Transport.send(mailMessage);
            res.status = true;
        } catch (MessagingException ex) {
            res.status = false;
            res.message = ex.getMessage();
            Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    private Properties setMailProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.ssl.enable", "true");
        return prop;
    }

    private void SetMailMime(Message mimeMessage, String recepient, String subject, String body) {
        try {
            mimeMessage.setFrom(new InternetAddress(p.getProperty("EmailAddress")));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            mimeMessage.setContent(body, "text/html");
            mimeMessage.setSubject(subject);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Session setMailSession(Properties prop) {
        Session mailSession = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(p.getProperty("EmailAddress"), p.getProperty("Password"));
            }
        });
        mailSession.setDebug(true);
        return mailSession;
    }
}