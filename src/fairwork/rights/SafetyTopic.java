package fairwork.rights;

/**
 * SafetyTopic , a right about safety at work
 * @author Salma Hussein
 */
public class SafetyTopic extends RightsTopic {

    public SafetyTopic(String title, String explanation) {
        super("Safety", title, explanation);
    }

    @Override
    public String getWhereToReport() {
        return "Health and Safety Authority (HSA)";
    }
}