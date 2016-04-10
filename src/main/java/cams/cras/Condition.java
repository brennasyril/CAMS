/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.cras;

import javafx.beans.property.SimpleStringProperty;
import main.java.cams.database.DatabaseController;


/**
 *
 * @author Brenna
 */
public class Condition {
    private SimpleStringProperty condCode, condName, condDesc; 
    DatabaseController db = new DatabaseController("cras");
    
    public Condition(String code, String name, String desc) {
        this.condCode = new SimpleStringProperty(code); 
        this.condName = new SimpleStringProperty(name); 
        this.condDesc = new SimpleStringProperty(desc); 
    }
    
    public void addCondition() {

        String query = "INSERT INTO CRA_Condition_BR (CRACondCode, CRACondName, CRACondDesc) " + "VALUES('"
                + this.getCondCode() + "','" + this.getCondName()
                + "','" + this.getCondDesc() + "')";
        db.executeInsert(query);
    }

    public void updateCondition() {
        String query = "UPDATE CRA_Condition_BR " + "SET CRACondCode = '"
                + this.getCondCode() + "', CRACondName = '" + this.getCondName()
                + "', CRACondDesc = '" + this.getCondDesc() + "' WHERE (CRACondCode = '"
                + this.getCondCode() + "')";

        db.executeStatement(query);
    }

    public void deleteCondition() {
        String query = "DELETE FROM `cras`.`cra_Condition_br` WHERE `cra_Condition_br`.`CRACondCode` = '" + this.getCondCode() + "'";

        db.executeStatement(query);
    }
    
    public String getCondCode() {
        return condCode.get(); 
    }
    
    public void setCondCode(String code) {
        condCode.set(code);  
    }
    
    public SimpleStringProperty condCodeProperty() {
        return condCode; 
    }
    
    public String getCondName() {
        return condName.get(); 
    }
    
    public void setCondName(String name) {
        condName.set(name); 
    }
    
    public SimpleStringProperty condNameProperty() {
        return condName; 
    }
    
    public String getCondDesc() {
        return condDesc.get(); 
    }
    
    public void setCondDesc(String desc) {
        condDesc.set(desc); 
    }
    
    public SimpleStringProperty condDescProperty() {
        return condDesc; 
    }
}
