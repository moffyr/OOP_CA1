/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.reports;
import java.util.ArrayList;

/**
 * @version 1
 * @author mahaj
 * 
 */
public class CommunityPost {
    //here, protected is used to allow for inheritance
    protected int postId;
    protected String title;
    protected String description;
    protected String category;
    protected boolean isAnonymous;
    protected int likes;
    protected ArrayList<Comment> comments;
    
    //constructors
    public CommunityPost(){
        this.comments = new ArrayList<>();
    }
    
    public CommunityPost(int postId, String title, String description, String category, boolean isAnonymous){
        this.postId = postId;
        this.title= title;
        this.description= description;
        this.category = category;
        this.isAnonymous = isAnonymous;
        this.likes =0;
        this.comments = new ArrayList<>();
    }
    //getters and setters
    public int getPostId(){
        return postId;
    }
    public void setPostId(int postId){
        this.postId = postId;
    }
    
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    
    public boolean isAnonymous(){
        return isAnonymous;
    }
    public void setAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }
    
    public int getLikes(){
        return likes;
    }
    public void setLikes(int likes){
        this.likes = likes;
    }
    
    public ArrayList<Comment> getComments() {
        return comments;
    }
    
    //action methods
    public void addLike(){
        likes++;
    }
    
    public void addComment(Comment c){
        comments.add(c);
    }
    
    //polymorphic methods
    public String getPostType(){
        return "post";
    }
    public String getDetails(){
        String author = isAnonymous? "Anonymous" : "Community Member";
        return  "Post ID: " + postId + "\nType: " + getPostType() + "\nCategory: " + category + "\nTitle: " + title
                + "\nPosted by: " + author + "\nLikes: " + likes + "\nDescription: " + description;
    }
}
