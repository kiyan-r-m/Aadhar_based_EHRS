/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author admin
 */
@FacesValidator("confirmPasswordValidator")
public class ConfirmPasswordValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
        String password = ((UIInput) component.findComponent("password")).getLocalValue().toString();
        if (password == null) {
            throw new ValidatorException(new FacesMessage("Passwords are empty"));
        } else if (!password.equals(value)) {
            FacesMessage message = new FacesMessage("Passwords do not match.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(component.getClientId(), message);
            facesContext.renderResponse();
        }
    }
    
}
