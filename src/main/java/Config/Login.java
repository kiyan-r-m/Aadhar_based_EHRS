/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Config;

import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;   

;

/**
 *
 * @author krdmo
 */
@Named(value = "login")
@RequestScoped
public class Login {

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
    @Inject
    private SecurityContext securityContext;
    @Inject
    private ExternalContext externalContext;
    @Inject
    private FacesContext facesContext;
    @Inject
    IdentityStoreHandler identitystore;

    public String submit() {
        switch (continueAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Login failed", null));
                break;
            case SUCCESS:
                try {
                CredentialValidationResult result = identitystore
                        .validate(new UsernamePasswordCredential(email, password));
                Set<String> roles = result.getCallerGroups();
                if (roles.contains("Admin")) {
                    return "admin/home.jsf";
                } else if (roles.contains("User")) {
                    return "user/home.jsf";
                } else if (roles.contains("Doctor")) {
                    return "doctor/home.jsf";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
            case NOT_DONE:
// Doesn’t happen here
        }
        return "login.jsf";
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
}
