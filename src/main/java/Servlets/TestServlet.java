/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Beans.EmailClientLocal;
import Beans.userBeanLocal;
import com.mycompany.aadhar_based_ehrs.ResponseModel;
import com.mycompany.aadhar_based_ehrs.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
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
    EmailClientLocal mail;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");
            out.println("</head>");
            out.println("<body>");

            ResponseModel<Collection<Users>> users = ubl.getAllUsers();

            if (users.status == true) {
                for (Users user : users.data) {
                    out.println(user.getUsername());
                }
            } else {
                System.out.println(users.message);
            }
//            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");

//            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
//            String datestring = "28-04-2001";
//            Date date = new Date(28 - 04 - 2001);
//            Users u = new Users();
//            u.setUsername("krmorena");
//            u.setEmailid("krmorena@gmail.com");
//            u.setPassword("qwer1234");
//            u.setContactno(BigInteger.valueOf(9876987555L));
//            u.setRoleid(1);
//            u.setAadharno(BigInteger.valueOf(123476548765L));
//            u.setGender("male");
//            u.setDob(date);
//            u.setBloodgroupid(1);
//            ResponseModel r = ubl.addUser(u);
//            if(r.status == true) {
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
        processRequest(request, response);
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
        processRequest(request, response);
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
