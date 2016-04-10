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
public class CropPeriod {
    private SimpleStringProperty periodCode, startDate, endDate, periodDesc; 
    DatabaseController db = new DatabaseController("cras");
    
    public CropPeriod(String code, String start, String end, String desc) {
        this.periodCode = new SimpleStringProperty(code); 
        this.startDate = new SimpleStringProperty(start); 
        this.endDate = new SimpleStringProperty(end); 
        this.periodDesc = new SimpleStringProperty(desc); 
    }
    
    public void addCropPeriod() {

        String query = "INSERT INTO CRA_CropPeriod_BR (CRACropPerCode, CRAPeriodDesc, CRAStartDate, CRAEndDate) " + "VALUES('"
                + this.getPeriodCode() + "','" + this.getPeriodDesc()
                + "','" +this.getStartDate()  + "','"
                + this.getEndDate() + "')";
        db.executeInsert(query);
    }

    public void updateCropPeriod() {
        String query = "UPDATE CRA_CropPeriod_BR " + "SET CRACropPerCode = '"
                + this.getPeriodCode() + "', CRAPeriodDesc = '" + this.getPeriodDesc()
                + "', CRAStartDate = '" + this.getStartDate() + "', CRAEndDate = '"
                + this.getEndDate() + "' WHERE (CRACropPerCode = '"
                + this.getPeriodCode() + "')";

        db.executeStatement(query);
    }

    public void deleteCropPeriod() {
        String query = "DELETE FROM `cras`.`cra_cropperiod_br` WHERE `cra_CropPeriod_br`.`CRACropPerCode` = '" + this.getPeriodCode() + "'";

        db.executeStatement(query);
    }
    
    public String getPeriodCode() {
        return  periodCode.get(); 
    }
    
    public void setPeriodCode(String code) {
        periodCode.set(code); 
    }
    
    public SimpleStringProperty periodCodeProperty() {
        return periodCode; 
    }
    
    public String getStartDate() {
        return startDate.get(); 
    }
    
    public void setStartDate(String start) {
        startDate.set(start); 
    }
    
    public SimpleStringProperty startDateProperty() {
        return startDate; 
    }
    
    public String getEndDate() {
        return endDate.get(); 
    }
    
    public void setEndDate(String end) {
        endDate.set(end); 
    }
    
    public SimpleStringProperty endDateProperty() {
        return endDate; 
    }
    
    public String getPeriodDesc() {
        return periodDesc.get(); 
    }
    
    public void setPeriodDesc(String desc) {
        periodDesc.set(desc); 
    }
    
    public SimpleStringProperty periodDescProperty() {
        return periodDesc; 
    }
    
    
}
