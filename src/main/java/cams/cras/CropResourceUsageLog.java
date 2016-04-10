/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.cras;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import main.java.cams.database.DatabaseController;

/**
 *
 * @author Brenna
 */
public class CropResourceUsageLog {
    private SimpleStringProperty resourceRef; 
    private SimpleDoubleProperty resourceCost, quantityUsed; 
    private SimpleStringProperty cropCode, resourceCode, seasonCode, observation, comment; 
    DatabaseController db = new DatabaseController("cras");
    
    public CropResourceUsageLog(String ref, String crop, String resource, String season, double quantity, double cost, String obs, String comm) {
        this.resourceRef = new SimpleStringProperty(ref); 
        this.cropCode = new SimpleStringProperty(crop); 
        this.resourceCode = new SimpleStringProperty(resource); 
        this.seasonCode = new SimpleStringProperty(season); 
        this.quantityUsed = new SimpleDoubleProperty(quantity); 
        this.resourceCost = new SimpleDoubleProperty(cost); 
        this.observation = new SimpleStringProperty(obs); 
        this.comment = new SimpleStringProperty(comm); 
    }
    
    public void addCrop() {

        String query = "INSERT INTO CRA_CropResLog_BR (CRACropResRef, CRACropCode, CRAItemCode, CRASeasonCode, CRAQTY, CRACost, CRAObservation, CRAComment) " + "VALUES('"
                + this.getResourceRef() + "','" + this.getCropCode()
                + "','" + this.getResourceCode() + "','"
                + this.getSeasonCode() + "','" + this.getQuantityUsed() + "','" + this.getResourceCost() + "','" 
                + this.getObservation() + "','" + this.getComment() + "')";
        db.executeInsert(query);
    }

    public void updateCrop() {
        String query = "UPDATE CRA_CropResLog_BR " + "SET CRACropResRef = '"
                + this.getResourceRef() + "', CRACropCode= '" + this.getCropCode()
                + "', CRAItemCode = '" + this.getResourceCode() + "', CRASeasonCode = '"
                + this.getSeasonCode() +  "', CRAQTY = '"
                + this.getQuantityUsed() + "', CRACost = '"
                + this.getResourceCost() + "', CRAObservation = '"
                + this.getObservation() +  "', CRAComment = '"
                + this.getComment() + "' WHERE (CRACropResRef = '"
                + this.getResourceRef() + "')";

        db.executeStatement(query);
    }

    public void deleteCrop() {
        String query = "DELETE FROM `cras`.`cra_cropreslog_br` WHERE `cra_CropResLog_br`.`CRACropResRef` = '" + this.getResourceRef() + "'";

        db.executeStatement(query);
    }
    
    public String getResourceRef() {
        return resourceRef.get(); 
    }
    
    public void setResourceRef(String ref) {
        resourceRef.set(ref); 
    }
    
    public SimpleStringProperty resourceRefProperty() {
        return resourceRef; 
    }
    
    public String getCropCode() {
        return cropCode.get(); 
    }
    
    public void setCropCode(String code) {
        cropCode.set(code); 
    }
    
    public SimpleStringProperty cropCodeProperty() {
        return cropCode; 
    }
    
    public String getResourceCode() {
        return resourceCode.get(); 
    }
    
    public void setResourceCode(String code) {
        resourceCode.set(code); 
    }
    
    public SimpleStringProperty resourceCodeProperty() {
        return resourceCode; 
    }
    
    public String getSeasonCode() {
        return seasonCode.get(); 
    }
    
    public void setSeasonCode(String code) {
        seasonCode.set(code); 
    }
    
    public SimpleStringProperty seasonCodeProperty() {
        return seasonCode; 
    }
    
    public double getQuantityUsed() {
        return quantityUsed.get(); 
    }
    
    public void setQuantityUsed(double quantity) {
        quantityUsed.set(quantity); 
    }
    
    public SimpleDoubleProperty quantityUsedProperty() {
        return quantityUsed; 
    }
    
    public double getResourceCost() {
        return resourceCost.get(); 
    }
    
    public void setResourceCost(double cost) {
        resourceCost.set(cost); 
    }
    
    public SimpleDoubleProperty resourceCostProperty() {
        return resourceCost; 
    }
    
    public String getObservation() {
        return observation.get(); 
    }
    
    public void setObservation(String obs) {
        observation.set(obs); 
    }
    
    public SimpleStringProperty observationProperty() {
        return observation; 
    }
    
    public String getComment() {
        return  comment.get(); 
    }
    
    public void setComment(String comm) {
        comment.set(comm);
    }
    
    public SimpleStringProperty commentProperty() {
        return comment; 
    }
}
