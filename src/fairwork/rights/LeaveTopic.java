package fairwork.rights;

/**
 * LeaveTopic - a right about leave e.g. holidays or sick leave2
 * @author Salma Hussein
 */
public class LeaveTopic extends RightsTopic {

    public LeaveTopic(String title, String explanation) {
        super("Leave", title, explanation);
    }

    @Override
    public String getWhereToReport() {
        return "Workplace Relations Commission (WRC)";
    }
}