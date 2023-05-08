///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
// */
//package Config;
//
//import Beans.userBeanLocal;
//import Entities.ResponseModel;
//import Entities.Users;
//import java.util.HashSet;
//import java.util.Set;
//import javax.ejb.EJB;
//import javax.inject.Named;
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import javax.security.enterprise.credential.Credential;
//import javax.security.enterprise.credential.UsernamePasswordCredential;
//import javax.security.enterprise.identitystore.CredentialValidationResult;
//import javax.security.enterprise.identitystore.IdentityStore;
//import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
//
//
///**
// *
// * @author krdmo
// */
//@Named(value = "userServiceIdentityStore")
//@ApplicationScoped
//public class UserServiceIdentityStore implements IdentityStore{
//    
//    @EJB
//    userBeanLocal ubl;
//    
//    @Inject
//    private Pbkdf2PasswordHash passwordHash;
//
//    @Override
//    public CredentialValidationResult validate(Credential credential){
//        
//        ResponseModel<Users> res = new ResponseModel<>();
//        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
//        String username = login.getCaller();
//        String password = login.getPasswordAsString();
//        String hashedPassword = passwordHash.generate(password.toCharArray());
//        if(isAadharNo(username))
//        {
//            res = ubl.getUserByAadharPassword(username, hashedPassword);
//            if(res.status)
//            {
//                Set<String> roles = new HashSet<>();
//                roles.add(res.data.getRoleId().getRoleName());
//                return new CredentialValidationResult(
//                        res.data.getUsername(),
//                        roles
//                );
//            }
//            else
//                return CredentialValidationResult.INVALID_RESULT;
//        }
//        else
//        {
//            res = ubl.getUserByUsernamePassword(username, hashedPassword);
//            if(res.status)
//            {
//                Set<String> roles = new HashSet<>();
//                roles.add(res.data.getRoleId().getRoleName());
//                return new CredentialValidationResult(
//                        res.data.getUsername(),
//                        roles
//                );
//            }
//            else
//                return CredentialValidationResult.INVALID_RESULT;
//        }
//        
//        
//    }
//    
//    private boolean isAadharNo(String check)
//    {
//        for(int i = 0; i<check.length(); i++)
//        {
//            if(check.charAt(i)<'0' || check.charAt(i)>'9')
//                return false;
//        }
//        return true;
//    }
//
//}
