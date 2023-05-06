/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Config;


import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;


@CustomFormAuthenticationMechanismDefinition(
    loginToContinue = @LoginToContinue(
        loginPage = "/login.jsf",
        errorPage = ""
    )
)

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/ehrsystem",
        callerQuery = "select password from users where username = ?",
        groupsQuery = "select role_name from roles r join users u on r.roleid = u.role_id where u.username = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class
    )

@FacesConfig
@ApplicationScoped
public class ApplicationConfig {

    /**
     * Creates a new instance of ApplicationConfig
     */
    public ApplicationConfig() {
    }
    
}
