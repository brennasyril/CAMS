/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.cras;

/**
 *
 * @author Brenna
 */
public class MasterSchedule {
    private int schedID; 
    private String comment; 
    
    public MasterSchedule(int sched, String comm) {
        schedID = sched; 
        comment = comm; 
    }
    
    public int getID() {
        return schedID; 
    }
    
    public void setID(int id) {
        schedID = id; 
    }
    
    public String getComment() {
        return comment; 
    }
    
    public void setComment(String comm) {
        comment = comm; 
    }
}
