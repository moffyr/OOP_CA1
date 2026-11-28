/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.rights;

/**
 *
 * @author salma
 */
public class RightsTopic {
    private String category;
    private String title;
    private String explanation;

    public RightsTopic(String category, String title, String explanation) {
        this.category = category;
        this.title = title;
        this.explanation = explanation;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
        
    }

    public String getExplanation() {
        return explanation;
    }
        // who the worker should contact: each child class changes this
    
    public String getWhereToReport() {
        return "Citizens Information";
    }

    // shows the topic as text on the screen 
    @Override
    public String toString() {
        return title + " (" + category + ")\n" + explanation
                + "\nWho to contact: " + getWhereToReport();
    }
    
            
            
    
}

