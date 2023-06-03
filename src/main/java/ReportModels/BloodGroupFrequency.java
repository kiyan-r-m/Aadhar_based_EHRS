/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ReportModels;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;

/**
 *
 * @author krdmo
 */
public class BloodGroupFrequency implements Serializable{
    String blood_group_name;
    long frequency;

    public String getBlood_group_name() {
        return blood_group_name;
    }

    public void setBlood_group_name(String blood_group_name) {
        this.blood_group_name = blood_group_name;
    }

   
    

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }
    
}
