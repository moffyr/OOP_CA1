/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.reports;


/**
 *  Date: 23/11/2026
 * @author Ritika Mahajan
 * @version rev 1
 */
public class Comment {
    //Encapsulation
    private int commentId;
    private String text;
    private boolean isAnonymous;
    
    //constructors
    public Comment(){
    }
    public Comment(int commentId, String text, boolean isAnonymous){
        this.commentId = commentId;
        this.text = text;
        this.isAnonymous = isAnonymous;
    }
        
    //getter and setter methods
    public int getCommentId(){
        return commentId;
    }
    public void setCommentId(int commentId){
        this.commentId = commentId;
    }
        
    public String getText(){
        return text;
    }   
    public void setText(String text){
        this.text = text;
    }
        
    public boolean isAnonymous(){
        return isAnonymous;
    }
    public void setAnonymous(boolean isAnonymous){
        this.isAnonymous = isAnonymous;
    }
    public String getCommentDetails(){
        String author = isAnonymous? "Anonymous" : "Community Member";
        return "[" +author+ "] " +text;
    }
 
}
