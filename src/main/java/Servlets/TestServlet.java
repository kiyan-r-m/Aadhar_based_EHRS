/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Beans.AdminBeanLocal;
import Beans.userBeanLocal;
import Entities.*;
import ReportModels.BloodGroupFrequency;
import ReportModels.DateWiseCaseFrequency;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @EJB
    userBeanLocal ubl;

    @EJB
    AdminBeanLocal abl;
    @PersistenceContext(unitName = "my_persistence")
    EntityManager em;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            Collection<BloodGroupFrequency> respo = abl.getBloodGroupFrequency();
            for(BloodGroupFrequency data : respo){
                out.println(data.getBlood_group_name());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
            String datestring = "01-04-2023";
            Date date = sdf.parse(datestring);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            Long countdoctors = abl.getAllUsersFrequency(3);
            out.println(countdoctors);
            LocalDate ld = LocalDate.of(2023,01,22);
            Collection<DateWiseCaseFrequency> cf = abl.getCasesFrequency(null, ld, null);
            for(DateWiseCaseFrequency data : cf){
                out.println(data.getFrequency().toString());
            }
            
            

//            ubl.addUser(new Users("Vesu", new Pincodes(395006)));
//            ubl.updateAddresses(new Addresses(1, "Vesu", new Pincodes(395006)));
//            ResponseModel<Collection<Users>> cm = ubl.getAllUsers();
//            if (cm.status) {
//                for (Users c : cm.data) {
//                    out.println(c.getUsername() + " " + c.getRoleId().getRoleName() + " " + c.getAddressId().getAddress() + " " + c.getBloodGroupId().getBloodGroupName() +"\n");
//                }
//            }
//            ResponseModel<Collection<Users>> users = ubl.getAllUsers();
//            if (users.status == true) {
//                for (Users user : users.data) {
//                    out.println(user.getUsername());
//                }
//            } else {
//                System.out.println(users.message);
//            }
//            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            

            ResponseModel<Users> res = new ResponseModel<>();

//            String password = passwordHash.generate( "secret12345".toCharArray());
//            out.println(password);
//            res = ubl.getUserByUsernamePassword("kiyanmorena", password);
//            out.println(res.data);
//            Users u = new Users();
//            u.setUsername("kiyanmorena");
//            u.setPassword("secret12345");
//            u.setAadharCardNo(BigInteger.valueOf(123412341234L));
//            u.setContactNo(BigInteger.valueOf(9824663176L));
//            u.setRoleId(em.find(Roles.class, 1));
//            u.setGender("male");
//            u.setDob(date);
//            u.setBloodGroupId(em.find(BloodGroups.class, 1));
//            u.setAddressId(em.find(Addresses.class, 1));
//            ubl.adduser1(u);
//            Users u = new Users();
//            u.setUserId(3);
//            u.setUsername("Charmi Modi");
//            u.setEmailid("charmi@gmail.com");
//            u.setPassword("Chr@1234");
//            u.setContactNo(BigInteger.valueOf(9016687973L));
//            u.setRoleId(new Roles(1));
//            u.setAadharCardNo(BigInteger.valueOf(123476548758L));
//            u.setGender("Female");
//            u.setDob(sqlDate);
////            Collection<BloodGroups> b = new ArrayList<>();
////            b.add(new BloodGroups(1, "AB+"));
//            u.setBloodGroupId(new BloodGroups(1));
//            Collection<Diseases> d = new ArrayList<>();
//            d.add(new Diseases(1));
//            u.setDiseasesCollection(d);
//            Collection<Allergies> a = new ArrayList<>();
//            a.add(new Allergies(1));
//            u.setAllergiesCollection(a);
//            u.setAddressId(new Addresses(1));
//            ResponseModel r = ubl.addUser(u);
//            if (r.status == true) {
//                System.out.println("User added successfully");
//            } else {
//                System.out.println(r.message);
//            }
//            // email send
//            ResponseModel res = mail.sendMail("charmimodi242@gmail.com", "Subject: Testing", "<h1>Hello!</h1><p>Body: This is message</p>");
//            if(res.status == true) {
//                System.out.println("Mail Sent");
//            } else {
//                System.out.println("Mail Sent");
//                System.out.println(res.message);
//            }

//            // Forget Password
//            ResponseModel res = ubl.SendMailForForgetPassword("charmimodi242@gmail.com", response);
//            if (res.status == true) {
//                System.out.println("Forget Password");
//                ResponseModel r = ubl.ForgetPassword(request, response, 3, "e9b61adf-cf8a-4330-b4c5-6f00d3608ba4", "Chr@1234");
//                if (r.status != true) {
//                    System.out.println("Hello Error!!");
//                    System.out.println(r.message);
//                }
//            } else {
//                System.out.println("Error!!");
//                System.out.println(res.message);
//            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
