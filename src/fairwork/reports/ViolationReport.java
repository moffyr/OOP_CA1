/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.reports;

/**
 *
 * @author mahaj
 */

//subclass (ViolationReport) inherits from superclass (CommunityPost)
public class ViolationReport extends CommunityPost{
    //encapusulated variable
    private String  incidentDate;
    
    //constructors
    public ViolationReport() {
        super();
    }
    public ViolationReport(int postId, String title, String description, String category, boolean isAnonymous, String incidentDate){
        super(postId, title, description, category, isAnonymous);
        this.incidentDate = incidentDate;
    }
    
    //getters and setters
    public String getIncidentDate(){
        return incidentDate;
    }
    public void setIncidentDate(String incidentDate){
        this.incidentDate = incidentDate;
    }
    
    //overriding methods- polymorphism
    @Override
    public String getPostType(){
        return "violation";
    }
    @Override
    public String getDetails(){
        return super.getDetails() + "\nDate of Incident: " + incidentDate;
    }
}
