/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.support;

/**
 * Anything that can be written to a text file as one line.
 * WorkShift and PayRecord both implement this, so EmploymentManager
 * can save either of them the same way.
 * 
 * @author Shourya Uppal
 */
public interface FileStorable {
    // returns this object as one comma separated line for the file
    String toFileLine();
}
