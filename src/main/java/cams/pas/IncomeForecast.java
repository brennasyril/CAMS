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
public class IncomeForecast {
    private String forecastRef, cropCode, seasonCode, perfRef, resRef, netIncRef; 
    private double netIncome, confidenceBand; 
    
    public IncomeForecast(String ref, String crop, String season, String performance, String resourceRef, String incRef, double income, double band) {
        forecastRef = ref; 
        cropCode = crop; 
        seasonCode = season; 
        perfRef = performance; 
        resRef = resourceRef; 
        netIncRef = incRef; 
        netIncome = income; confidenceBand = band; 
    }
    
    public String getForecastRef() {
        return forecastRef; 
    }
    
    public void setForecastRef(String ref) {
        forecastRef = ref; 
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
    
    public String getPerformance() {
        return perfRef; 
    }
    
    public void setPerformance(String ref) {
        perfRef = ref; 
    }
    
    public String getResourceRef() {
        return resRef; 
    }
    
    public void setResourceRef(String ref) {
        resRef = ref; 
    }
    
    public double getIncome() {
        return netIncome; 
    }
    
    public void setIncome(double income) {
        netIncome = income; 
    }
    
    public String getIncomeRef() {
        return netIncRef;
    }
    
    public void setIncomeRef(String ref) {
        netIncRef = ref; 
    }
    
    public double getConfidenceBand() {
        return confidenceBand; 
    }
    
    public void setConfidenceBand(double band) {
        confidenceBand = band; 
    }
}
