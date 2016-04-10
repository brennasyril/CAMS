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
public class Season {
    private String seasonCode, comment; 
    private int startDate, endDate; 
    
    public Season(String code, int start, int end, String comm) {
        seasonCode = code;  
        startDate = start; 
        endDate = end; 
        comment = comm; 
    }
    
    public String getCode() {
        return seasonCode; 
    }
    
    public void setCode(String code) {
        seasonCode = code; 
    }
    
    public int getStart() {
        return startDate; 
    }
    
    public void setStart(int start) {
        startDate = start; 
    }
    
    public int getEnd() {
        return endDate; 
    }
    
    public void setEnd(int end) {
        endDate = end; 
    }
    
    public String getComment() {
        return comment; 
    }
    
    public void setComment(String comm) {
        comment = comm; 
    }
}
