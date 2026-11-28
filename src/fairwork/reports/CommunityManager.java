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
public class CommunityManager {
    //encapsulation
    private ArrayList<CommunityPost> posts;
    private int nextPostId;
    private int nextCommentId;
    
    //constructor
    public CommunityManager() {
        posts = new ArrayList<>();
        nextPostId = 1;
        nextCommentId = 1;
    }
    
    public int generatePostId(){
        return nextPostId++;
    }
    
    //adding and displaying posts
    public void addPosts(CommunityPost post){
        if(post != null){
            posts.add(post);
        }   
    }
    //DISPLAY ALL
    public ArrayList<CommunityPost> gatAllPosts() {
        return posts;
    }
    
    //FILTER BY CATEGORY
    public ArrayList<CommunityPost> filterByCategory(String category){
        ArrayList<CommunityPost> results = new ArrayList<>();
        for (CommunityPost p: posts){
            if (category.equalsIgnoreCase("All") || p.getCategory().equalsIgnoreCase(category)){
                results.add(p);
            }
        }
        return results;
    }
    
    //SEARCH POSTS
    public ArrayList<CommunityPost> searchPost(String keyword){
        ArrayList<CommunityPost> results = new ArrayList<>();
        String term = keyword.trim().toLowerCase();
        for (CommunityPost p: posts){
            if(p.getTitle().toLowerCase().contains(term) || p.getCategory().toLowerCase().contains(term) || p.getDetails().toLowerCase().contains(term)){
                results.add(p);
            }
        }
        return results;
    }
    
    //DELETE POSTS
    public boolean deletePostsById(int postId){
        for(int i=0; i<posts.size(); i++){
            if(posts.get(i).getPostId() == postId){
                posts.remove(i);
                return true;
            }
        
        }
        return false;
    }
    
    //LIKING
    public boolean likePostById(int postId){
        for(CommunityPost p: posts){
            if(p.getPostId() == postId){
                p.addLike();
                return true;
            }
        }
        return false;
    }
    
    //COMMENTING
    public boolean addCommentToPost(int postId, String text, boolean isAnonymous){
        for(CommunityPost p: posts){
            if(p.getPostId() == postId){
                Comment c = new Comment(nextCommentId++, text,isAnonymous);
                p.addComment(c);
                return true;
            }
        }
        return false;
    }
    
}
