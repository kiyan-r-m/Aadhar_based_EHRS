/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIs;

import Beans.userBeanLocal;
import Config.Login;
import Entities.ResponseModel;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;

@Named(value = "forgotPasswordManagedBean")
@SessionScoped
public class ForgotPasswordManagedBean implements Serializable {
    String email;
    String oldPassword;
    String password;
    String confirmPassword;
    @EJB
    userBeanLocal ubl;
    @Inject
    ExternalContext externalContent;
    @Inject
    Login login;
    
    public ForgotPasswordManagedBean() {
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    
    public void submit() {
        ResponseModel res = ubl.SendMailForForgetPassword(email, (HttpServletRequest) externalContent.getRequest());
        if (res.status) {
            successMessage("Reset Password", "We have sent you a link to reset your password on the mail id you entered. So check your mail inbox to reset the password");
        } else {
            errorMessage("Error", res.message);
        }
    }
    
    public void resetPassword() {
        ResponseModel res = ubl.ForgetPassword((HttpServletRequest) externalContent.getRequest(), password);
        if (res.status) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
            } catch (IOException ex) {
                Logger.getLogger(ForgotPasswordManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            errorMessage("Error", res.message);
        }
    }
    
    public void changePassword() {
        ResponseModel res = ubl.ChangePassword(login.getUserId(), oldPassword, password);
        if (res.status) {
            login.setPassword(password);
            successMessage("Change Password", "Password changed successfully!");
        } else {
            errorMessage("Error", res.message);
        }
        PrimeFaces.current().executeScript("PF('manageChangePasswordDialog').hide();");
    }
    
    public void successMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    private void errorMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
