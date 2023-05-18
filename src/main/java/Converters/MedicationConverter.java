/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Converters;

import Beans.AdminBeanLocal;
import Entities.CommonMedications;
import Entities.ResponseModel;
import javax.faces.component.UIComponent;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author admin
 */
public class MedicationConverter implements Converter {

    @EJB AdminBeanLocal abl;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value.isEmpty() || value == null) {
            return null;
        }
        ResponseModel<CommonMedications> res = abl.getCommonMedicationById(Integer.parseInt(value));
        if (res.status) {
            return res.data;
        } else {
            throw new ConverterException("Converter Error", new Throwable(res.getMessage()));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return null;
        }
        
        CommonMedications cm = (CommonMedications) value;
        return cm.getMedicationId().toString();
    }
    
}
