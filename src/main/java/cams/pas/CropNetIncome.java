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
public class CropNetIncome {
    private String incomeRef, cropCode, perfRef, resRef, seasonCode, comment; 
    private double netIncome; 
    
    public CropNetIncome(String ref, String crop, String perf, String resource, String season, double income, String comm) {
        incomeRef = ref; 
        cropCode = crop; 
        perfRef = perf; 
        resRef = resource; 
        seasonCode = season; 
        comment = comm; 
        netIncome = income; 
    }
    
    public String getRef() {
        return incomeRef; 
    }
    
    public void setRef(String ref) {
        incomeRef = ref; 
    }
    
    public String getCrop() {
        return cropCode; 
    }
    
    public void setCrop(String crop) {
        cropCode = crop;
    }
    
    public String getResourceUsage() {
        return resRef; 
    }
    
    public void setResourceUsage(String resource) {
        resRef = resource; 
    }
    
    public String getSeason() {
        return seasonCode; 
    }
    
    public void setSeason(String season) {
        seasonCode = season; 
    }
    
    public String getPerformance() {
        return perfRef; 
    }
    
    public void setPerformance(String perf) {
        perfRef = perf; 
    }
    
    public String getComment() {
        return comment; 
    }
    
    public void setComment(String comm) {
        comment = comm; 
    }
    
    public double getNetIncome() {
        return netIncome; 
    }
    
    public void setNetIncome(double income) {
        netIncome = income; 
    }
}
