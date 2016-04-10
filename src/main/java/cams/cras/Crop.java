/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.cras;

import java.io.File;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import main.java.cams.database.DatabaseController;

/**
 *
 * @author Brenna
 */
public class Crop {

    private SimpleStringProperty cropCode, cropName, cropPer, plantFam;
    DatabaseController db = new DatabaseController("cras");

    public Crop(String code, String name, String period, String family) {
        this.cropCode = new SimpleStringProperty(code);
        this.cropName = new SimpleStringProperty(name);
        this.cropPer = new SimpleStringProperty(period);
        this.plantFam = new SimpleStringProperty(family);
    }

    public void addCrop() {

        String query = "INSERT INTO CRA_Crop_BR (CRACropCode, CRACropName, CRACropPer, CRAPlantFam, CRACropImage) " + "VALUES('"
                + this.getCropCode() + "','" + this.getCropName()
                + "','" + this.getCropPer() + "','"
                + this.getPlantFam() + "')";
        db.executeInsert(query);
    }

    public void updateCrop() {
        String query = "UPDATE CRA_Crop_BR " + "SET CRACropCode = '"
                + this.getCropCode() + "', CRACropName = '" + this.getCropName()
                + "', CRACropPer = '" + this.getCropPer() + "', CRAPlantFam = '"
                + this.getPlantFam() + "' WHERE (CRACropCode = '"
                + this.getCropCode() + "')";

        db.executeStatement(query);
    }

    public void deleteCrop() {
        String query = "DELETE FROM `cras`.`cra_crop_br` WHERE `cra_Crop_br`.`CRACropCode` = '" + this.getCropCode() + "'";

        db.executeStatement(query);
    }

    public String getCropCode() {
        return cropCode.get();
    }
   

    public SimpleStringProperty cropCodeProperty() {
        return cropCode;
    }

    public void setCropCode(String code) {
        cropCode.set(code);
    }

    public String getCropName() {
        return cropName.get();
    }

    public SimpleStringProperty cropNameProperty() {
        return cropName;
    }

    public void setCropName(String name) {
        cropName.set(name);
    }

    public String getCropPer() {
        return cropPer.get();
    }

    public SimpleStringProperty cropPerProperty() {
        return cropPer;
    }

    public void setCropPer(String period) {
        cropPer.set(period);
    }

    public String getPlantFam() {
        return plantFam.get();
    }

    public SimpleStringProperty plantFamProperty() {
        return plantFam;
    }

    public void setPlantFam(String fam) {
        plantFam.set(fam);
    }
}
