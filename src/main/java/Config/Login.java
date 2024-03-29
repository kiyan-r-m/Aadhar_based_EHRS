/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Config;

import Beans.userBeanLocal;
import Entities.ResponseModel;
import Entities.Users;
import java.io.Serializable;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

;

/**
 *
 * @author krdmo
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }
    @NotNull
    private String email;
    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    private int userId;
    @Inject
    private SecurityContext securityContext;
    @Inject
    private ExternalContext externalContext;
    @Inject
    private FacesContext facesContext;
    @Inject
    IdentityStoreHandler identitystore;
    @EJB
    userBeanLocal ubl;

    public void submit() {
        switch (continueAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                if (this.userId == 0) {
                    ResponseModel<Users> res = ubl.getUserByUsernamePassword(email, password);
                    if (res.status) {
                        this.userId = res.data.getUserId();
                    }
                }
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Username or Password is wrong"));
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Username or Password is wrong"));
                break;
            case SUCCESS:
                try {
                CredentialValidationResult result = identitystore
                        .validate(new UsernamePasswordCredential(email, password));
                Set<String> roles = result.getCallerGroups();
                ResponseModel<Users> res = ubl.getUserByUsernamePassword(email, password);
                if (res.status) {
                    this.userId = res.data.getUserId();
                }
                if (roles.contains("Admin")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("admin/home.jsf");
                    break;
                } else if (roles.contains("User")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("user/home.jsf");
                    break;
                } else if (roles.contains("Doctor")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("doctor/home.jsf");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
            case NOT_DONE:
// Doesn’t happen here
        }
    }

    private AuthenticationStatus continueAuthentication() {
        return securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(
                        new UsernamePasswordCredential(email, password))
        );
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
