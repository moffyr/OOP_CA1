/*
 *
 * @author Shourya Uppal, 24289001
 * FileStorable.java
 * 
 */
package fairwork.support;

/*
 * Anything that can be written to a text file as one line.
 * WorkShift and PayRecord both implement this, so EmploymentManager
 * can save either of them the same way.
 * 
 */
public interface FileStorable {
    // returns this object as one comma separated line for the file
    String toFileLine();
}
