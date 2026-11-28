/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.reports;

/**
 *
 * @author mahaj
 */

//subclass (SolutionSuggestion) inherits from superclass (CommunityPost)
public class SolutionSuggestion extends CommunityPost{
    //encapusulated variable
    private String problemStatement;
    private String proposedSolution;
    
    //constructors
    public SolutionSuggestion() {
        super();
    }
    public SolutionSuggestion(int postId, String title, String description, String category, boolean isAnonymous, String problemStatement, String proposedSolution){
        super(postId, title, description, category, isAnonymous);
        this.problemStatement = problemStatement;
        this.proposedSolution = proposedSolution;
    }
    
    //getters and setters
    public String getProblemStatement(){
        return problemStatement;
    }
    public void setProblemStatement(String problemStatement){
        this.problemStatement = problemStatement;
    }
    
    public String getProposedSolution() {
        return proposedSolution;
    }
    public void setProposedSolution(String proposedSolution){
        this.proposedSolution = proposedSolution;
    }
    
    //overriding methods- polymorphism
    @Override
    public String getPostType(){
        return "Solution";
    }
    @Override
    public String getDetails(){
        return super.getDetails() + "\nProblem Statement: " + problemStatement + "\nProposed Solution : " + proposedSolution;
    }
}
