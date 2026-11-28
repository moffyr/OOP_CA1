package fairwork.rights;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * TopicManager - keeps all  the topics in an ArrayList.
 * Add, display, search, delete, and save or load from a file.
 * @author Salma Hussein
 */
public class TopicManager {

    private ArrayList<RightsTopic> topics;
    private String fileName = "topics.txt";

    public TopicManager() {
        topics = new ArrayList<>();
        loadFromFile();   // get the saved topics when the app opens
    }

    // ADD a topic to the list, then save
    public void addTopic(RightsTopic t) {
        topics.add(t);
        saveToFile();
    }

    // DISPLAY all topics as one piece of text
    public String displayTopics() {
        String result = "";
        for (RightsTopic t : topics) {
            result = result + t.toString() + "\n\n";
        }
        return result;
    }

    // SEARCH : returns every topic whose title has the keyword in it
    public ArrayList<RightsTopic> searchTopic(String keyword) {
        ArrayList<RightsTopic> found = new ArrayList<>();
        for (RightsTopic t : topics) {
            if (t.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                found.add(t);
            }
        }
        return found;
    }

    // DELETE the topic with this title. Returns true if it was found.
    public boolean deleteTopic(String title) {
        for (int i = 0; i < topics.size(); i++) {
            if (topics.get(i).getTitle().equalsIgnoreCase(title)) {
                topics.remove(i);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public ArrayList<RightsTopic> getTopics() {
        return topics;
    }

    // WRITE every topic to the file, one per line: category|title|explanation
    public void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (RightsTopic t : topics) {
                out.println(t.getCategory() + "|" + t.getTitle() + "|" + t.getExplanation());
            }
        } catch (IOException e) {
            System.out.println("Could not save topics: " + e.getMessage());
        }
    }

    // READ the file and rebuild the topics
    public void loadFromFile() {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String category = parts[0];
                    String title = parts[1];
                    String explanation = parts[2];
                    if (category.equals("Pay")) {
                        topics.add(new PayTopic(title, explanation));
                    } else if (category.equals("Safety")) {
                        topics.add(new SafetyTopic(title, explanation));
                    } else {
                        topics.add(new LeaveTopic(title, explanation));
                    }
                }
            }
        } catch (IOException e) {
            // no file yet the first time the app runs - that's fine
        }
    }
}