/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.pas;
/**
 *
 * @author Brenna
 */
public class CropPerformance {
    private String perfRef, cropCode, seasonCode, yieldUnit, comment; 
    private int quantityYield; 
    private double revenueGen; 
    
    public CropPerformance(String ref, String crop, String season, String unit, String comm, int yield, double revenue) {
        perfRef = ref; 
        cropCode = crop; 
        seasonCode = season; 
        yieldUnit = unit; 
        comment = comm; 
        quantityYield = yield; 
        revenueGen = revenue; 
    }
    
    public String getPerfRef() {
        return perfRef; 
    }
    
    public void setPerfRef(String ref) {
        perfRef = ref; 
    }
    
    public String getCrop() {
        return cropCode; 
    }
    
    public void setCrop(String crop) {
        cropCode = crop; 
    }
    
    public String getSeason() {
        return seasonCode; 
    }
    
    public void setSeason(String season) {
        seasonCode = season; 
    }
    
    public String getComment() {
        return comment; 
    }
    
    public void setComment(String comm) {
        comment = comm; 
    }
    
    public String getYieldUnit() {
        return yieldUnit; 
    }
    
    public void setYieldUnit(String unit) {
        yieldUnit = unit; 
    }
    
    public int getYield() {
        return quantityYield; 
    }
    
    public void setYield(int yield) {
        quantityYield = yield; 
    }
    
    public double getRevenue() {
        return revenueGen; 
    }
    
    public void setRevenue(double revenue) {
        revenueGen = revenue; 
    }
}
